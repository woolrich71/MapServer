package se.woolrich.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import se.woolrich.demo.entities.*;
import se.woolrich.demo.model.Livemap;
import se.woolrich.demo.model.LocationList;
import se.woolrich.demo.model.Trip;
import se.woolrich.demo.model.TripList;
import se.woolrich.demo.oauth.OAuth;
import se.woolrich.demo.repository.UserRepository;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    OAuth oauth;

    @Autowired
    UserRepository userRepository;


    @PostMapping(value = "/login" )
    @ResponseBody
    public ResponseEntity<User> login(@RequestBody User user) {

        if (user == null)
            return new ResponseEntity("User is missing", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        if(user.getDestination() == null)
            return new ResponseEntity("position is missing", new HttpHeaders(), HttpStatus.BAD_REQUEST);



        Optional<LocationList> locationList = getLocation(user.getDestination());

        user.setAddress(
                locationList
                    .map(l->  l.getCoordLocation().getName())
                    .orElse("No such address"));

        userRepository.deleteAll();
        userRepository.save(user);


        return new ResponseEntity(user, new HttpHeaders(), HttpStatus.OK);
    }



    @PostMapping(value = "/ping" )
    @ResponseBody
    public ResponseEntity<Result> ping(@RequestBody Ping ping) {

        // find user

        Optional<User> user = userRepository.findByUuid(ping.getUuid()).stream().findFirst();

        if(user.isPresent()) {
            // if(user == null)
            //    return new ResponseEntity("No such user", new HttpHeaders(), HttpStatus.OK);


            Result time = getTime(user.get(), ping);


            return new ResponseEntity(time, new HttpHeaders(), HttpStatus.OK);
        }
        return null;
    }


    @RequestMapping("/livemap")
    public ResponseEntity<?> home() {

        Position position = new Position();
        position.setLat(57.707145f);
        position.setLng(11.967824f);
        getLocation(position);
        Optional<Livemap> livemap = getLivemap(position);
        return new ResponseEntity(livemap.orElse(null), new HttpHeaders(), HttpStatus.OK);
    }

    class BoundingBox {

        float width = 0.001f;
        private final Position position;

        public BoundingBox(Position position) {
            this.position = position;
        }

        String getMaxx() {
            return get(position.getLng(), width);
        }
        String getMinx() {
            return get(position.getLng(), -width);
        }
        String getMaxy() {
            return get(position.getLat(), width);

        }
        String getMiny() {
            return get(position.getLat(), -width);
        }

        String get(float xy, float w){
            float v = (xy + w) * 1000000f;
            return Integer.toString((int)v);
        }


    }

    private Optional<Livemap> getLivemap(Position position) {

        BoundingBox bb = new BoundingBox(position);

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

     Result getTime(User user, Ping ping) {


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


        Result other = new Result();
        other.setStart(LocalTime.now());
        other.setStop(LocalTime.now());
        other.setDuration(Duration.between(other.getStart(), other.getStop()));
        other.setTime(other.getDuration().toString());
        try {
            TripList tripList = oauth.get("/trip", parameters, TripList.class);
            if (tripList.getTrip() != null) {
                Optional<Result> first = tripList.getTrip().stream().map(t -> getSection(t))
                        .sorted(Comparator.comparing(Result::getDuration)).
                                findFirst();
                return first.orElse(other);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

       return other;

    }




    private Optional<LocationList> getLocation(Position position) {
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







    // curl -v  -H "Content-Type: application/json" -X POST  -d '{"lat":57.714332,"lng":12.060843}' http://192.168.0.11:8080/getNearbyAddress

    @PostMapping(value = "/getNearbyAddress" )
    @ResponseBody
    public ResponseEntity<?> getNearbyAddress(@RequestBody Position position) {
        Optional<LocationList> location = getLocation(position);
        return new ResponseEntity(location.orElse(null), new HttpHeaders(), HttpStatus.OK);
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
