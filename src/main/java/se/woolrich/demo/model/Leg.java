package se.woolrich.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({
        "name",
        "type",
        "Origin",
        "Destination",
        "sname",
        "id",
        "direction",
        "fgColor",
        "bgColor",
        "stroke",
        "accessibility",
        "JourneyDetailRef"
})
public class Leg {

    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("Origin")
    private Origin origin;
    @JsonProperty("Destination")
    private Destination destination;
    @JsonProperty("sname")
    private String sname;
    @JsonProperty("id")
    private String id;
    @JsonProperty("direction")
    private String direction;
    @JsonProperty("fgColor")
    private String fgColor;
    @JsonProperty("bgColor")
    private String bgColor;
    @JsonProperty("stroke")
    private String stroke;
    @JsonProperty("accessibility")
    private String accessibility;
    @JsonProperty("JourneyDetailRef")
    private JourneyDetailRef journeyDetailRef;

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

    @JsonProperty("Origin")
    public Origin getOrigin() {
        return origin;
    }

    @JsonProperty("Origin")
    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    @JsonProperty("Destination")
    public Destination getDestination() {
        return destination;
    }

    @JsonProperty("Destination")
    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    @JsonProperty("sname")
    public String getSname() {
        return sname;
    }

    @JsonProperty("sname")
    public void setSname(String sname) {
        this.sname = sname;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("direction")
    public String getDirection() {
        return direction;
    }

    @JsonProperty("direction")
    public void setDirection(String direction) {
        this.direction = direction;
    }

    @JsonProperty("fgColor")
    public String getFgColor() {
        return fgColor;
    }

    @JsonProperty("fgColor")
    public void setFgColor(String fgColor) {
        this.fgColor = fgColor;
    }

    @JsonProperty("bgColor")
    public String getBgColor() {
        return bgColor;
    }

    @JsonProperty("bgColor")
    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    @JsonProperty("stroke")
    public String getStroke() {
        return stroke;
    }

    @JsonProperty("stroke")
    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    @JsonProperty("accessibility")
    public String getAccessibility() {
        return accessibility;
    }

    @JsonProperty("accessibility")
    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    @JsonProperty("JourneyDetailRef")
    public JourneyDetailRef getJourneyDetailRef() {
        return journeyDetailRef;
    }

    @JsonProperty("JourneyDetailRef")
    public void setJourneyDetailRef(JourneyDetailRef journeyDetailRef) {
        this.journeyDetailRef = journeyDetailRef;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("type", type).append("origin", origin).append("destination", destination).append("sname", sname).append("id", id).append("direction", direction).append("fgColor", fgColor).append("bgColor", bgColor).append("stroke", stroke).append("accessibility", accessibility).append("journeyDetailRef", journeyDetailRef).toString();
    }

}