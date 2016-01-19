package com.navbicycle.graphStation;

import com.navbicycle.domain.VeturiloStation;
import com.navbicycle.osrm.DistanceMatrixProvider;
import com.navbicycle.osrm.model.Coordinate;
import com.navbicycle.osrm.model.RouterResponse;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcinstepnowski on 11.01.2016.
 */
public class GraphStationCreator {

    public DirectedWeightedMultigraph<VeturiloStation, WeightedVeturiloStationsPath> createGraph(List<VeturiloStation> stations) {

        DirectedWeightedMultigraph<VeturiloStation, WeightedVeturiloStationsPath> graph =
                new DirectedWeightedMultigraph<>(WeightedVeturiloStationsPath.class);

        for (VeturiloStation place : stations) {
            graph.addVertex(place);
        }

        DistanceMatrixProvider distanceMatrixProvider = new DistanceMatrixProvider();
        RouterResponse routerResponse = distanceMatrixProvider.downloadMatrix(this.convertVeturiloStationsToCoordinates(stations));
        List< List<Integer>> distanceMatrix = routerResponse.getTableDistance();
        int placesSize = stations.size();
        for (int i = 0; i < placesSize; i++) {
            for (int j = 0; j < placesSize; j++) {
                if(i != j) {
                    WeightedVeturiloStationsPath edge = graph.addEdge(stations.get(i), stations.get(j));
                    int seconds = distanceMatrix.get(i).get(j).intValue() / 10;
                    graph.setEdgeWeight(edge, this.calculateCost(seconds));
                }
            }
        }

        return graph;
    }

    private Coordinate convertVeturiloStationToCoordinate(VeturiloStation station) {
        return new Coordinate(station.getLat(), station.getLng());
    }

    private List<Coordinate> convertVeturiloStationsToCoordinates(List<VeturiloStation> stations) {
        List<Coordinate> coordinates = new ArrayList<>();
        for (VeturiloStation station : stations) {
            coordinates.add(this.convertVeturiloStationToCoordinate(station));
        }

        return coordinates;
    }

    protected double calculateCost(int seconds) {
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
