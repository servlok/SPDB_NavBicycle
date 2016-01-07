package com.navbicycle.osrm;

import com.navbicycle.osrm.model.RouterResponse;
import org.junit.Test;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by marcinstepnowski on 07.01.2016.
 */
public class ResponseReaderTest {

    private String testString = "{\"status\":200,\"source_coordinates\":[[52.230165,21.012618],[52.231436,21.021379],[52.238374,21.006592]]}";

    @Test
    public void shouldReadStatus() {
        RouterResponse routerResponse = ResponseReader.read(testString);
        assertEquals(200, routerResponse.getStatus());
    }

    @Test
    public void shouldReadSources() {
        RouterResponse routerResponse = ResponseReader.read(testString);
        List<List <Double> > sources = routerResponse.getSourceCoordinates();
        assertEquals(3, sources.size());
        assertEquals(52.230165, sources.get(0).get(0));
        assertEquals(21.012618, sources.get(0).get(1));
        assertEquals(52.231436, sources.get(1).get(0));
        assertEquals(21.021379, sources.get(1).get(1));
        assertEquals(52.238374, sources.get(2).get(0));
        assertEquals(21.006592, sources.get(2).get(1));
    }
}
