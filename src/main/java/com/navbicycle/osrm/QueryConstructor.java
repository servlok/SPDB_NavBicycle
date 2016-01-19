package com.navbicycle.osrm;

import com.navbicycle.osrm.model.Coordinate;
import io.mikael.urlbuilder.UrlBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by marcinstepnowski on 07.01.2016.
 */
public class QueryConstructor {

    private static String LOCAL_ROUTER_URL = "http://localhost:5000";

    private static String ROUTER_URL = "http://router.project-osrm.org";

    static URL urlWithCoordinates(List<Coordinate> coordinates) {
        UrlBuilder builder = UrlBuilder.fromString(LOCAL_ROUTER_URL + "/table");
        for (Coordinate coordinate : coordinates) {
            String string = coordinate.getLattitude() + "," + coordinate.getLongtitude();
            builder =  builder.addParameter("loc", string);
        }

        try {
            return builder.toUri().toURL();
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
