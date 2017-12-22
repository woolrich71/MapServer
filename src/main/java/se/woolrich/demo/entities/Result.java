package se.woolrich.demo.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import se.woolrich.demo.model.Vehicle;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@JsonSerialize
public class Result {


    @Id
    String id;
    String time;
    LocalTime start;
    LocalTime stop;
    List<Section> sections;
    List<Vehicle> vehicles;
    Duration duration;

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Section> getSections() {
        return sections == null ? List.of(): sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getStop() {
        return stop;
    }

    public void setStop(LocalTime stop) {
        this.stop = stop;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }

    public List<Vehicle> getVehicles() {
        return vehicles == null ? List.of(): vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
