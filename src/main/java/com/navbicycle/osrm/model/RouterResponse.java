package com.navbicycle.osrm.model;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcinstepnowski on 07.01.2016.
 */
public class RouterResponse {

    private int status;

    private List<Coordinate> destinationCoordinates;
    private List<Coordinate> sourceCoordinates;
    private List<List<Integer>> tableDistance;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Coordinate> getSourceCoordinates() {
        return sourceCoordinates;
    }

    public void setSourceCoordinates(List<Coordinate> sourceCoordinates) {
        this.sourceCoordinates = sourceCoordinates;
    }

    public List<Coordinate> getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public void setDestinationCoordinates(List<Coordinate> destinationCoordinates) {
        this.destinationCoordinates = destinationCoordinates;
    }

    public List<List<Integer>> getTableDistance() {
        return tableDistance;
    }

    @JsonSetter("distance_table")
    public void setTableDistance(List<List<Integer>> tableDistance) {
        this.tableDistance = tableDistance;
    }

    @JsonSetter("source_coordinates")
    public void setSourceCoordinatesList(List<List<Double>> sourceCoordinates) {
        setSourceCoordinates(locationListFromListListDouble(sourceCoordinates));
    }

    @JsonSetter("destination_coordinates")
    public void setDestinationCoordinatesList(List<List<Double>> sourceCoordinates) {
        setDestinationCoordinates(locationListFromListListDouble(sourceCoordinates));
    }

    private List<Coordinate>locationListFromListListDouble(List<List<Double>> listListDouble) {
        List<Coordinate> coordinateList = new ArrayList<Coordinate>(listListDouble.size());
        for (List<Double> list : listListDouble) {
            coordinateList.add(new Coordinate(list));
        }

        return coordinateList;
    }

}
