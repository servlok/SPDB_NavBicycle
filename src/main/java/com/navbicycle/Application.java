package com.navbicycle;

import com.navbicycle.graphStation.GraphStationCreator;
import com.navbicycle.osrm.model.Coordinate;
import com.navbicycle.resource.RouteResource;
import com.navbicycle.veturilo.PlaceProvider;
import com.navbicycle.veturilo.model.Place;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        PlaceProvider placeProvider = new PlaceProvider();
        List<Place> places = placeProvider.downloadPlaces();
        System.out.println(places.size());

        List<Coordinate> coordinates = new ArrayList<Coordinate>(2);
        coordinates.add(new Coordinate(52.230165, 21.012618));
        coordinates.add(new Coordinate(52.231436, 21.021379));

//        DistanceMatrixProvider matrixProvider = new DistanceMatrixProvider();
//        RouterResponse routerResponse = matrixProvider.downloadMatrix(coordinates);
//        System.out.println(routerResponse);

        GraphStationCreator graphStationCreator = new GraphStationCreator();
        DirectedWeightedMultigraph<Place, DefaultWeightedEdge> graph = graphStationCreator.createGraph(
                places.stream().limit(5).collect(Collectors.<Place>toList())
        );
        System.out.println(graph);

        List<DefaultWeightedEdge> path = DijkstraShortestPath.findPathBetween(graph, places.get(0), places.get(3));
        System.out.println(path);


    }

    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(RouteResource.class);
        return s;
    }
}
