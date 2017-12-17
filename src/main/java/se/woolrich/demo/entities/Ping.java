package se.woolrich.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonSerialize
public class Ping {

    String uuid;
    Position position;
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd-HH:mm:ss", timezone = "CET")
    private Date timeStamp = new Date();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

}
