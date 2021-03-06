package com.navbicycle.osrm;

import com.navbicycle.osrm.model.Coordinate;
import org.junit.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by marcinstepnowski on 07.01.2016.
 */
public class QueryConstructorTest {

    @Test
    public void shouldCreateQuery() {
        List<Coordinate> coordinates = new ArrayList<Coordinate>(2);
        coordinates.add(new Coordinate(52.230165, 21.012618));
        coordinates.add(new Coordinate(52.231436, 21.021379));

        URL url = QueryConstructor.urlWithCoordinates(coordinates);
        String string = url.toString();
        assertEquals("http://localhost:5000/table?" +
                "loc=52.230165%2C21.012618&loc=52.231436%2C21.021379",string);

    }
}
