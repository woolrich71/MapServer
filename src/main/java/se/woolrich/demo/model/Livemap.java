package se.woolrich.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import se.woolrich.demo.util.JsonLocalTimeDeserializer;

import java.time.LocalTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonRootName("livemap")
public class Livemap {

    @JsonDeserialize(using = JsonLocalTimeDeserializer.class)
    LocalTime time;
    int maxx;
    int minx;
    int maxy;
    int miny;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    List<Vehicle> vehicles;


    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getMaxx() {
        return maxx;
    }

    public void setMaxx(int maxx) {
        this.maxx = maxx;
    }

    public int getMinx() {
        return minx;
    }

    public void setMinx(int minx) {
        this.minx = minx;
    }

    public int getMaxy() {
        return maxy;
    }

    public void setMaxy(int maxy) {
        this.maxy = maxy;
    }

    public int getMiny() {
        return miny;
    }

    public void setMiny(int miny) {
        this.miny = miny;
    }

    public List<Vehicle> getVehicles() {
        return vehicles == null ? List.of() : vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
