package com.navbicycle.graphStation;

import com.navbicycle.domain.VeturiloStation;
import com.navbicycle.osrm.DistanceMatrixProvider;
import com.navbicycle.osrm.model.Coordinate;
import com.navbicycle.osrm.model.RouterResponse;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by marcinstepnowski on 11.01.2016.
 */
public class GraphStationCreator {

    protected static double GRAPH_WEIGHT_DELTA = 0.01;

    public DirectedWeightedMultigraph<VeturiloStation, WeightedVeturiloStationsPath> createGraph(List<VeturiloStation> stations,
                                                                                                 Map<Integer, Double> distanceStations) {

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
                    edge.setAdditionalCost(GRAPH_WEIGHT_DELTA * distanceStations.get(stations.get(j).getUid()));
                    graph.setEdgeWeight(edge, this.calculateCost(seconds,
                            distanceStations.get(stations.get(j).getUid())));
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

    protected double calculateCost(int seconds, Double dist) {
        int cost;
        if (seconds <= 1200) {
            cost = 0;
        } else {
            cost = 1;
            for (int i = 3600; i < seconds; i += 3600) {
                switch (i) {
                    case 3600:
                        cost += 3;
                        break;

                    case 7200:
                        cost += 5;
                        break;

                    default:
                        cost += 7;
                        break;
                }

            }

        }

        return (double) cost + GRAPH_WEIGHT_DELTA * dist;
    }
}
