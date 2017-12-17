package se.woolrich.demo.entities;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Test
    public void testUser() {

        User user = new User();
        user.setUuid("1234-5444-1234");
        Position position = new Position();
        position.setLat(57.714280f);
        position.setLng(12.060773f);
        user.setDestination(position);

        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);


        try {
            System.out.println(mapper.writeValueAsString(user));


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void testPing() {

        Ping ping = new Ping();
        ping.setUuid("1234-5444-1234");
        Position position = new Position();
        position.setLat(57.714280f);
        position.setLng(12.060773f);
        ping.setPosition(position);

        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);


        try {
            System.out.println(mapper.writeValueAsString(ping));


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
