package com.navbicycle.graphStation;

import com.navbicycle.osrm.DistanceMatrixProvider;
import com.navbicycle.osrm.model.Coordinate;
import com.navbicycle.osrm.model.RouterResponse;
import com.navbicycle.veturilo.model.Place;
import org.jgrapht.graph.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcinstepnowski on 11.01.2016.
 */
public class GraphStationCreator {

    public DirectedWeightedMultigraph<Place, DefaultWeightedEdge> createGraph(List<Place> places) {

        DirectedWeightedMultigraph<Place, DefaultWeightedEdge> graph =
                new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);

        for (Place place : places) {
            graph.addVertex(place);
        }

        DistanceMatrixProvider distanceMatrixProvider = new DistanceMatrixProvider();
        RouterResponse routerResponse = distanceMatrixProvider.downloadMatrix(this.convertPlacesToCoordinates(places));
        List< List<Integer>> distanceMatrix = routerResponse.getTableDistance();
        int placesSize = places.size();
        for (int i = 0; i < placesSize; i++) {
            for (int j = 0; j < placesSize; j++) {
                if(i != j) {
                    System.out.print("From " + i + "to " + j);
                    DefaultWeightedEdge edge = graph.addEdge(places.get(i), places.get(j));
                    int seconds = distanceMatrix.get(i).get(j).intValue() / 10;
                    graph.setEdgeWeight(edge, this.calculateCost(seconds));
                }
            }
        }

        return graph;
    }

    private Coordinate convertPlaceToCoordinate(Place place) {
        return new Coordinate(place.getLattitude(), place.getLongtitude());
    }

    private List<Coordinate> convertPlacesToCoordinates(List<Place> places) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (Place place : places) {
            coordinates.add(this.convertPlaceToCoordinate(place));
        }

        return coordinates;
    }

    private double calculateCost(int seconds) {
        int minutes = seconds / 60;
        int cost;
        if(minutes < 20) {
            cost = 0;
        } else {
            cost = 1;
            int hours = minutes / 60;
            for (int i = 1; i < hours ; i++) {
                switch (i) {
                    case 1:
                        cost += 3;
                        break;

                    case 2:
                        cost += 5;
                        break;

                    default:
                        cost += 7;
                        break;
                }

            }

        }

        return (double) cost;
    }
}
