package com.navbicycle.domain;

import java.util.List;

public class RoutePoints {
    private List<VerturiloStation> verturiloStations;
    private List<Double> costs;

    public RoutePoints(List<VerturiloStation> verturiloStations, List<Double> costs) {
        this.verturiloStations = verturiloStations;
        this.costs = costs;
    }

    public List<VerturiloStation> getVerturiloStations() {
        return verturiloStations;
    }

    public List<Double> getCosts() {
        return costs;
    }

}
