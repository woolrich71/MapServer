package se.woolrich.demo.util;

import se.woolrich.demo.entities.Position;

public class BoundingBox {

    private float width = 0.03f;
    private final Position position;

    public BoundingBox(Position position) {
        this.position = position;
    }

    public String getMaxx() {
        return get(position.getLng(), width);
    }
    public String getMinx() {
        return get(position.getLng(), -width);
    }

    public String getMaxy() {
        return get(position.getLat(), width);

    }
    public String getMiny() {
        return get(position.getLat(), -width);
    }

    private String get(float xy, float w){
        float v = (xy + w) * 1000000f;
        return Integer.toString((int)v);
    }
}
