package se.woolrich.demo.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class CoordLocation {

    private String name;
    private String type;
    private String lat;
    private String lon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "CoordLocation: {"+
                "\n\tname:"+name+
                "\n\ttype:"+type+
                "\n\tlat:"+lat+
                "\n\tlon:"+lon+
                "\n}";


    }
}
