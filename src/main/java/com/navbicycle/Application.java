package com.navbicycle;

import com.navbicycle.osrm.DistanceMatrixProvider;
import com.navbicycle.osrm.model.Coordinate;
import com.navbicycle.osrm.model.RouterResponse;
import com.navbicycle.veturilo.PlaceProvider;
import com.navbicycle.veturilo.model.Place;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        PlaceProvider placeProvider = new PlaceProvider();
        List<Place> places = placeProvider.downloadPlaces();
        System.out.println(places.size());

        List<Coordinate> coordinates = new ArrayList<Coordinate>(2);
        coordinates.add(new Coordinate(52.230165, 21.012618));
        coordinates.add(new Coordinate(52.231436, 21.021379));

        DistanceMatrixProvider matrixProvider = new DistanceMatrixProvider();
        RouterResponse routerResponse = matrixProvider.downloadMatrix(coordinates);
        System.out.println(routerResponse);

    }
}
