package com.navbicycle.osrm;

import com.navbicycle.osrm.model.Coordinate;
import com.navbicycle.osrm.model.RouterResponse;
import org.junit.Test;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by marcinstepnowski on 07.01.2016.
 */
public class ResponseReaderTest {

    @Test
    public void shouldReadResponse() {

        String testString = "{" +
                "\"status\":200," +
                "\"source_coordinates\":[[52.230165,21.012618],[52.231436,21.021379],[52.238374,21.006592]]," +
                "\"destination_coordinates\":[[52.230165,21.012618],[52.231436,21.021379],[52.238374,21.006592]]" +
                ",\"distance_table\":[[0,1119,767],[1027,0,1794],[1992,2307,0]]" +
                "}";

        RouterResponse routerResponse = ResponseReader.read(testString);
        assertEquals(200, routerResponse.getStatus());

        List<Coordinate> sources = routerResponse.getSourceCoordinates();
        assertEquals(3, sources.size());
        assertEquals(52.230165, sources.get(0).getLattitude());
        assertEquals(21.012618, sources.get(0).getLongtitude());
        assertEquals(52.231436, sources.get(1).getLattitude());
        assertEquals(21.021379, sources.get(1).getLongtitude());
        assertEquals(52.238374, sources.get(2).getLattitude());
        assertEquals(21.006592, sources.get(2).getLongtitude());

        List<Coordinate> destinations = routerResponse.getDestinationCoordinates();
        assertEquals(3, destinations.size());
        assertEquals(52.230165, destinations.get(0).getLattitude());
        assertEquals(21.012618, destinations.get(0).getLongtitude());
        assertEquals(52.231436, destinations.get(1).getLattitude());
        assertEquals(21.021379, destinations.get(1).getLongtitude());
        assertEquals(52.238374, destinations.get(2).getLattitude());
        assertEquals(21.006592, destinations.get(2).getLongtitude());

        List< List<Integer> > distance = routerResponse.getTableDistance();
        assertEquals(3, distance.size());
        assertEquals(0, distance.get(0).get(0).intValue());
        assertEquals(1119, distance.get(0).get(1).intValue());
        assertEquals(767, distance.get(0).get(2).intValue());

        assertEquals(1027, distance.get(1).get(0).intValue());
        assertEquals(0, distance.get(1).get(1).intValue());
        assertEquals(1794, distance.get(1).get(2).intValue());

        assertEquals(1992, distance.get(2).get(0).intValue());
        assertEquals(2307, distance.get(2).get(1).intValue());
        assertEquals(0, distance.get(2).get(2).intValue());
    }
}
