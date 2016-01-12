package com.navbicycle.osrm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navbicycle.osrm.model.Coordinate;
import com.navbicycle.osrm.model.RouterResponse;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by marcinstepnowski on 07.01.2016.
 */
public class DistanceMatrixProvider {

    public RouterResponse downloadMatrix(List<Coordinate> coordinates) {
        URL url = QueryConstructor.urlWithCoordinates(coordinates);
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(url, RouterResponse.class);
        } catch (IOException e) {
            return null;
        }
    }
}
