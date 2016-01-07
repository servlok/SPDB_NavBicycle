package com.navbicycle.osrm.model;

import java.util.List;

/**
 * Created by marcinstepnowski on 07.01.2016.
 */
public class Coordinate {

    private double lattitude;
    private double longtitude;

    public Coordinate(double lattitude, double longtitude) {
        this.lattitude = lattitude;
        this.longtitude = longtitude;
    }

    public Coordinate(List<Double> list) {
        this.lattitude = list.get(0).doubleValue();
        this.longtitude = list.get(1).doubleValue();
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
