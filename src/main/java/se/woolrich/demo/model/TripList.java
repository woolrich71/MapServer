package se.woolrich.demo.model;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
        "noNamespaceSchemaLocation",
        "servertime",
        "serverdate",
        "Trip"
})
@JsonRootName("TripList")
public class TripList {

    @JsonProperty("noNamespaceSchemaLocation")
    private String noNamespaceSchemaLocation;
    @JsonProperty("servertime")
    private String servertime;
    @JsonProperty("serverdate")
    private String serverdate;
    @JsonProperty("Trip")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Trip> trip;

    @JsonProperty("noNamespaceSchemaLocation")
    public String getNoNamespaceSchemaLocation() {
        return noNamespaceSchemaLocation;
    }

    @JsonProperty("noNamespaceSchemaLocation")
    public void setNoNamespaceSchemaLocation(String noNamespaceSchemaLocation) {
        this.noNamespaceSchemaLocation = noNamespaceSchemaLocation;
    }

    @JsonProperty("servertime")
    public String getServertime() {
        return servertime;
    }

    @JsonProperty("servertime")
    public void setServertime(String servertime) {
        this.servertime = servertime;
    }

    @JsonProperty("serverdate")
    public String getServerdate() {
        return serverdate;
    }

    @JsonProperty("serverdate")
    public void setServerdate(String serverdate) {
        this.serverdate = serverdate;
    }

    @JsonProperty("Trip")
    public List<Trip> getTrip() {
        return trip == null ? List.of() : trip;
    }

    @JsonProperty("Trip")
    public void setTrip(List<Trip> trip) {
        this.trip = trip;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("noNamespaceSchemaLocation", noNamespaceSchemaLocation).append("servertime", servertime).append("serverdate", serverdate).append("trip", trip).toString();
    }

}