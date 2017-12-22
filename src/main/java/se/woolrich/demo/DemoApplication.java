package se.woolrich.demo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
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

	@Bean
	@Primary
	public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
		ObjectMapper objectMapper = builder.createXmlMapper(false).build();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		return objectMapper;
	}



	@RequestMapping("/")
	String home() {
		return "Hello World!";
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
