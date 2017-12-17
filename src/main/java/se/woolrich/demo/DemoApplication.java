package se.woolrich.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.woolrich.demo.entities.Position;
import se.woolrich.demo.model.LocationList;
import se.woolrich.demo.oauth.OAuth;
import se.woolrich.demo.repository.PositionRepository;
import se.woolrich.demo.repository.UserRepository;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@ConfigurationProperties
@Configuration
public class DemoApplication implements CommandLineRunner {


	@Autowired
	PositionRepository repository;

	@Autowired
	UserRepository userRepository;


	@Autowired
	OAuth oauth;



	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	@RequestMapping(value = "/positions/get", method = RequestMethod.GET)
	public List<Position> getAllPositions() {
		return repository.findAll();
	}


	// curl -v  -H "Content-Type: application/json" -X POST  -d '{"lat":57.714802,"lng":12.055113}' http://localhost:8080/post
	@PostMapping(value = "/post" )
	@ResponseBody
	public ResponseEntity<?> addPosition(@RequestBody Position position) {
	//	repository.save(position);
		String path = "/location.nearbyaddress";

		Map<String, String> parameters = Map.of(
				"originCoordLat", Float.toString(position.getLat()),
				"originCoordLong", Float.toString(position.getLng()));

		try {
			LocationList locationList = oauth.get(path, parameters, LocationList.class);
			if(locationList != null)
				return new ResponseEntity(locationList.getCoordLocation(), new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity("Successfully added position", new HttpHeaders(), HttpStatus.OK);


	}



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	public void run(String... args) throws Exception {


		userRepository.findAll();

		userRepository.deleteAll();

		oauth.getAccessToken();

        InetAddress localHost = InetAddress.getLocalHost();

        System.out.println("Current IP address : " + localHost.getHostAddress());

    }





}
