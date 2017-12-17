
package se.woolrich.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
        "name",
        "type",
        "id",
        "time",
        "date",
        "track",
        "$",
        "routeIdx",
        "rtTime",
        "rtDate"
})
public class Destination {

    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private String id;
    @JsonProperty("time")
    private String time;
    @JsonProperty("date")
    private String date;
    @JsonProperty("track")
    private String track;
    @JsonProperty("$")
    private String $;
    @JsonProperty("routeIdx")
    private String routeIdx;
    @JsonProperty("rtTime")
    private String rtTime;
    @JsonProperty("rtDate")
    private String rtDate;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    public LocalTime getLocalTime() {
        return LocalTime.parse(time);
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("track")
    public String getTrack() {
        return track;
    }

    @JsonProperty("track")
    public void setTrack(String track) {
        this.track = track;
    }

    @JsonProperty("$")
    public String get$() {
        return $;
    }

    @JsonProperty("$")
    public void set$(String $) {
        this.$ = $;
    }

    @JsonProperty("routeIdx")
    public String getRouteIdx() {
        return routeIdx;
    }

    @JsonProperty("routeIdx")
    public void setRouteIdx(String routeIdx) {
        this.routeIdx = routeIdx;
    }

    @JsonProperty("rtTime")
    public String getRtTime() {
        return rtTime;
    }

    @JsonProperty("rtTime")
    public void setRtTime(String rtTime) {
        this.rtTime = rtTime;
    }

    @JsonProperty("rtDate")
    public String getRtDate() {
        return rtDate;
    }

    @JsonProperty("rtDate")
    public void setRtDate(String rtDate) {
        this.rtDate = rtDate;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("type", type).append("id", id).append("time", time).append("date", date).append("track", track).append("$", $).append("routeIdx", routeIdx).append("rtTime", rtTime).append("rtDate", rtDate).toString();
    }

}
