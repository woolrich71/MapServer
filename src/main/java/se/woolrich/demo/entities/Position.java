package se.woolrich.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;

import java.util.Date;

@JsonSerialize
@JsonPropertyOrder({
        "id",
        "lat",
        "lng",
        "timeStamp"
})
public class Position {
    @Id
    String id;
    float lat;
    float lng;


    public Position() { }

    public String getId() {
        return id;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLat() {
        return lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }


    public float getLng() {
        return lng;
    }


}
