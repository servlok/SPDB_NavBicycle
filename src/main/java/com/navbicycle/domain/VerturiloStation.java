package com.navbicycle.domain;

public class VerturiloStation extends Point {
    private String name;

    public VerturiloStation(Point point, String name) {
        super(point.getLat(), point.getLng());
        this.name = name;
    }

    public VerturiloStation(double lat, double lng, String name) {
        super(lat, lng);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
