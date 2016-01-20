package com.navbicycle.service;

import com.navbicycle.domain.Point;
import com.navbicycle.domain.RoutePoints;
import com.navbicycle.domain.VeturiloStation;
import com.navbicycle.graphStation.GraphStationCreator;
import com.navbicycle.graphStation.WeightedVeturiloStationsPath;
import com.navbicycle.veturilo.db.VeturiloStationRepository;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by servlok on 18.01.16.
 */
public class RoutePointsFinder {

    private static final float OFFSET_FRACTION = 0.1f;
    private static final float OFFSET = 1000;

    private VeturiloStationRepository veturiloStationRepository = new VeturiloStationRepository();

    public RoutePoints findRouteBetweenPoint(Point start, Point end) throws SQLException {
        VeturiloStation startingStation = veturiloStationRepository.findNereast(start.getLat(), start.getLng());
        VeturiloStation endingStation = veturiloStationRepository.findNereast(end.getLat(), end.getLng());

        List<VeturiloStation> availableStations = veturiloStationRepository.findAllInRectangleBetweenPoints(
                startingStation, endingStation, OFFSET
        );

        if (availableStations.isEmpty())
            return createEmptyRoutePoints();

        GraphStationCreator graphStationCreator = new GraphStationCreator();

        Map<Integer, Double> distanceStations = veturiloStationRepository
                .findAllDistanceBetweenTargetStationAndOtherStations(endingStation);
        DirectedWeightedMultigraph<VeturiloStation, WeightedVeturiloStationsPath> graph =
                graphStationCreator.createGraph(availableStations, distanceStations);

        List<WeightedVeturiloStationsPath> edges = DijkstraShortestPath.findPathBetween(
                graph, startingStation, endingStation
        );

        return createRoutePoints(edges);
    }

    private RoutePoints createEmptyRoutePoints() {
        return new RoutePoints(new ArrayList<>(), new ArrayList<>());
    }

    private RoutePoints createRoutePoints(List<WeightedVeturiloStationsPath> edges) {
        List<VeturiloStation> stations = new ArrayList<>();
        List<Double> costs = new ArrayList<>();

        if (edges.size() > 0) {
            stations.add(edges.get(0).getSource());
        }

        for (WeightedVeturiloStationsPath edge : edges) {
            stations.add(edge.getTarget());
            costs.add(edge.getCost());
        }

        return new RoutePoints(stations, costs);
    }
}
