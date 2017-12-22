package se.woolrich.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import se.woolrich.demo.entities.Ping;
import se.woolrich.demo.entities.Position;
import se.woolrich.demo.entities.Result;
import se.woolrich.demo.entities.User;
import se.woolrich.demo.model.Livemap;
import se.woolrich.demo.oauth.OAuth;
import se.woolrich.demo.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    RestService service;




    @PostMapping(value = "/login" )
    @ResponseBody
    public ResponseEntity<User> login(@RequestBody User user) {

        if (user == null)
            throw new IllegalArgumentException("error:User is missing");
        if(user.getDestination() == null)
            throw new IllegalArgumentException("error:User is missing");

        return new ResponseEntity<>(service.login(user), new HttpHeaders(), HttpStatus.OK);

    }


    @PostMapping(value = "/ping" )
    @ResponseBody
    public ResponseEntity<Result> ping(@RequestBody Ping ping) {
        return new ResponseEntity<>(service.ping(ping), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/livemap" )
    @ResponseBody
    public ResponseEntity<Livemap> livemap(@RequestBody Ping ping) {

        return new ResponseEntity<>(service.getLivemap(ping).orElse(null), new HttpHeaders(), HttpStatus.OK);
    }

    // curl -v  -H "Content-Type: application/json" -X POST  -d '{"lat":57.714332,"lng":12.060843}' http://192.168.0.11:8080/getNearbyAddress

    @PostMapping(value = "/getNearbyAddress" )
    @ResponseBody
    public ResponseEntity<?> getNearbyAddress(@RequestBody Position position) {
        return new ResponseEntity<>(service.getLocation(position).orElse(null), new HttpHeaders(), HttpStatus.OK);
    }



    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.BAD_REQUEST.value());

    }


}
