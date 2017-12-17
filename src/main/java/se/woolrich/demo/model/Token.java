package se.woolrich.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
        "TripList"
})
public class Token {

    @JsonProperty("TripList")
    private TripList tripList;

    @JsonProperty("TripList")
    public TripList getTripList() {
        return tripList;
    }

    @JsonProperty("TripList")
    public void setTripList(TripList tripList) {
        this.tripList = tripList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("tripList", tripList).toString();
    }
}

