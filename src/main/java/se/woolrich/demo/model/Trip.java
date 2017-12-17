package se.woolrich.demo.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
        "Leg"
})
public class Trip {

    @JsonProperty("Leg")
    private List<Leg> leg = null;

    @JsonProperty("Leg")
    public List<Leg> getLeg() {
        return leg;
    }

    @JsonProperty("Leg")
    public void setLeg(List<Leg> leg) {
        this.leg = leg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("leg", leg).toString();
    }
}

