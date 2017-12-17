package se.woolrich.demo.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationListTest {

    @Test
    public void testLocationList() {
        String jsonStr = "{\n" +
                "  \"LocationList\": {\n" +
                "    \"noNamespaceSchemaLocation\": \"http://api.rest.se/v1/hafasRestLocation.xsd\",\n" +
                "    \"servertime\": \"06:42\",\n" +
                "    \"serverdate\": \"2017-11-27\",\n" +
                "    \"CoordLocation\": {\n" +
                "      \"name\": \"Ormebäcksgatan 5C, 416 78 Göteborg\",\n" +
                "      \"lat\": \"57.714280\",\n" +
                "      \"lon\": \"12.060773\",\n" +
                "      \"type\": \"ADR\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);


        try {

           LocationList object = mapper.readerFor(LocationList.class).readValue(jsonStr);

            System.out.println(object);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
