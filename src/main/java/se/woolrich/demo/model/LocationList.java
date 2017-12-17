package se.woolrich.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.Date;


@JsonRootName("LocationList")
public class LocationList {

    private String errorText = null;

    private String error = null;

    private Date serverdate = null;

    private String servertime = null;

    //@JsonProperty("StopLocation")
   // private List<StopLocation> stopLocation = null;

    @JsonProperty("CoordLocation")
    private CoordLocation CoordLocation = null;
    //private List<CoordLocation> coordLocation = null;

    private String noNamespaceSchemaLocation = null;


    public LocationList() {

    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Date getServerdate() {
        return serverdate;
    }

    public void setServerdate(Date serverdate) {
        this.serverdate = serverdate;
    }

    public String getServertime() {
        return servertime;
    }

    public void setServertime(String servertime) {
        this.servertime = servertime;
    }


    public CoordLocation getCoordLocation() {
        return CoordLocation;
    }

    public void setCoordLocation(CoordLocation coordLocation) {
        this.CoordLocation = coordLocation;
    }

    public String getNoNamespaceSchemaLocation() {
        return noNamespaceSchemaLocation;
    }

    public void setNoNamespaceSchemaLocation(String noNamespaceSchemaLocation) {
        this.noNamespaceSchemaLocation = noNamespaceSchemaLocation;
    }

    @Override
    public String toString() {
        return  "LocationList: {\n"+
                CoordLocation.toString()+
                "\n}";

    }
}
