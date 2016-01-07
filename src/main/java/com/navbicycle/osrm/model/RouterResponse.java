package com.navbicycle.osrm.model;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

/**
 * Created by marcinstepnowski on 07.01.2016.
 */
public class RouterResponse {

    private int status;

    private List<List< Double >> sourceCoordinates;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<List<Double>> getSourceCoordinates() {
        return sourceCoordinates;
    }

    @JsonSetter("source_coordinates")
    public void setSourceCoordinates(List<List<Double>> sourceCoordinates) {
        this.sourceCoordinates = sourceCoordinates;
    }
}
