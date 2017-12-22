package se.woolrich.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.woolrich.demo.entities.*;
import se.woolrich.demo.model.Livemap;
import se.woolrich.demo.model.LocationList;
import se.woolrich.demo.model.Trip;
import se.woolrich.demo.model.TripList;
import se.woolrich.demo.oauth.OAuth;
import se.woolrich.demo.repository.UserRepository;
import se.woolrich.demo.util.BoundingBox;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestService {

    @Autowired
    OAuth oauth;

    @Autowired
    UserRepository userRepository;

    public User login(User user) {

        Optional<LocationList> locationList = getLocation(user.getDestination());

        user.setAddress(
                locationList
                        .map(l->  l.getCoordLocation().getName())
                        .orElse("No such address"));

        userRepository.deleteAll();
        userRepository.save(user);
        return user;
    }


    public Optional<LocationList> getLocation(Position position) {
        Map<String, String> parameters = Map.of(
                "originCoordLat", Float.toString(position.getLat()),
                "originCoordLong", Float.toString(position.getLng()));

        LocationList value = null;
        try {
            value = oauth.get("/location.nearbyaddress", parameters, LocationList.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.of(value);
    }


    public Result ping(Ping ping) {

        Optional<User> user = userRepository.findByUuid(ping.getUuid()).stream().findFirst();

        if(user.isPresent()) {
            try {
                return getResult(user.get(), ping);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Optional<Livemap> getLivemap(Ping ping) {

        Position origin = ping.getPosition();
        BoundingBox bb = new BoundingBox(origin);
        Map<String, String> parameters = Map.of(
                "maxx", bb.getMaxx(),
                "minx", bb.getMinx(),
                "maxy", bb.getMaxy(),
                "miny", bb.getMiny(),
                "onlyRealtime", "yes");

        Livemap value = null;
        try {
            value = oauth.get("/livemap", parameters, Livemap.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.of(value);
    }


    Result getResult(User user, Ping ping) throws  Exception {

        Position origin = ping.getPosition();

        String from = getLocation(origin)
                .map(l->  l.getCoordLocation().getName())
                .orElse("??");

        Position destination = user.getDestination();
        Map<String, String> parameters = Map.of(
                "originCoordLat", Float.toString(origin.getLat()),
                "originCoordLong", Float.toString(origin.getLng()),
                "originCoordName", from,
                "destCoordLat", Float.toString(destination.getLat()),
                "destCoordLong", Float.toString(destination.getLng()),
                "destCoordName", user.getAddress(),
                "numTrips", "1"
        );

        Optional<Livemap> livemap = getLivemap(ping);

        TripList tripList = oauth.get("/trip", parameters, TripList.class);
        Optional<Result> first = tripList.getTrip().stream().map(t -> getSection(t))
                .sorted(Comparator.comparing(Result::getDuration)).
                        findFirst();

        Result result = first.orElseThrow(() -> new Exception("no such trip"));
        result.setVehicles(livemap.orElse(null).getVehicles());
        return result;

    }




    private Result getSection(Trip t) {
        Result result = new Result();
        result.setSections(t.getLeg().stream().map(leg-> {
            Section section = new Section();
            section.setName(leg.getName());
            section.setType(leg.getType());
            section.setFrom(leg.getOrigin().getName());
            section.setStart(leg.getOrigin().getLocalTime());
            section.setTo(leg.getDestination().getName());
            section.setStop(leg.getDestination().getLocalTime());
            return section;
        }).collect(Collectors.toList()));
        result.setStart(result.getSections().stream().map(c->c.getStart()).min(LocalTime::compareTo).get());
        result.setStop(result.getSections().stream().map(c->c.getStop()).max(LocalTime::compareTo).get());
        result.setDuration(Duration.between(result.getStart(), result.getStop()));
        result.setTime(result.getDuration().toString());
        return result;
    }

}
