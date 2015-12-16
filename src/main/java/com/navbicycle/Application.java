package com.navbicycle;

import com.navbicycle.veturilo.PlaceProvider;
import com.navbicycle.veturilo.model.Place;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        PlaceProvider placeProvider = new PlaceProvider();
        List<Place> places = placeProvider.downloadPlaces();
        System.out.println(places.size());

    }
}
