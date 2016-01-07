package com.navbicycle.osrm;

import com.navbicycle.osrm.model.Coordinate;
import io.mikael.urlbuilder.UrlBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcinstepnowski on 07.01.2016.
 */
public class QueryConstructor {

    static URI uriWithCoordinates(List<Coordinate> coordinates) {
        UrlBuilder builder = UrlBuilder.fromString("http://router.project-osrm.org/table");
        for (Coordinate coordinate : coordinates) {
            String string = coordinate.getLattitude() + "," + coordinate.getLongtitude();
            builder =  builder.addParameter("loc", string);
        }

        return builder.toUri();
    }
}
