package se.woolrich.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonRootName("livemap")
public class Vehicle {


    String lcolor;
    @JsonProperty("prodclass")
    String prodClass;
    String bcolor;
    int direction;
    String name;
    String gid;
    int delay;
    int y;
    int x;

    public String getLcolor() {
        return lcolor;
    }

    public void setLcolor(String lcolor) {
        this.lcolor = lcolor;
    }

    public String getProdClass() {
        return prodClass;
    }

    public void setProdClass(String prodClass) {
        this.prodClass = prodClass;
    }

    public String getBcolor() {
        return bcolor;
    }

    public void setBcolor(String bcolor) {
        this.bcolor = bcolor;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
