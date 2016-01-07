package com.navbicycle.osrm.model;

import java.util.List;

/**
 * Created by marcinstepnowski on 07.01.2016.
 */
public class Location {

    private float lattitude;
    private float longtitude;

    public Location(float lattitude, float longtitude) {
        this.lattitude = lattitude;
        this.longtitude = longtitude;
    }

    public Location(List<Double> list) {
        this.lattitude = list.get(0).floatValue();
        this.longtitude = list.get(1).floatValue();
    }
}
