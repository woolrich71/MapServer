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

    private String name;
    private String type;

    @JsonProperty("Origin")
    private Origin origin;

    @JsonProperty("Destination")
    private Destination destination;
    private String sname;
    private String id;
    private String direction;
    private String fgColor;
    private String bgColor;
    private String stroke;
    private String accessibility;
    private JourneyDetailRef journeyDetailRef;

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

    @JsonProperty("Origin")
    public Origin getOrigin() {
        return origin;
    }
    @JsonProperty("Origin")
    public void setOrigin(Origin origin) {
        this.origin = origin;
    }
    @JsonProperty("Destination")
    public Destination getDestination() { return destination; }
    @JsonProperty("Destination")
    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public String getSname() {
        return sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDirection() {
        return direction;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public String getFgColor() {
        return fgColor;
    }
    public void setFgColor(String fgColor) {
        this.fgColor = fgColor;
    }
    public String getBgColor() {
        return bgColor;
    }
    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
    public String getStroke() {
        return stroke;
    }
    public void setStroke(String stroke) {
        this.stroke = stroke;
    }
    public String getAccessibility() {
        return accessibility;
    }
    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }
    public JourneyDetailRef getJourneyDetailRef() {
        return journeyDetailRef;
    }
    public void setJourneyDetailRef(JourneyDetailRef journeyDetailRef) {
        this.journeyDetailRef = journeyDetailRef;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("type", type).append("origin", origin).append("destination", destination).append("sname", sname).append("id", id).append("direction", direction).append("fgColor", fgColor).append("bgColor", bgColor).append("stroke", stroke).append("accessibility", accessibility).append("journeyDetailRef", journeyDetailRef).toString();
    }

}