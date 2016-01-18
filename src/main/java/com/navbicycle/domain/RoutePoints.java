package com.navbicycle.domain;

import java.util.List;

public class RoutePoints {
    private List<VeturiloStation> veturiloStations;
    private List<Double> costs;

    public RoutePoints(List<VeturiloStation> veturiloStations, List<Double> costs) {
        this.veturiloStations = veturiloStations;
        this.costs = costs;
    }

    public List<VeturiloStation> getVeturiloStations() {
        return veturiloStations;
    }

    public List<Double> getCosts() {
        return costs;
    }

}
