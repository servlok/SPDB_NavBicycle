package com.navbicycle.graphStation;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by servlok on 19.01.16.
 */
public class GraphStationCreatorTest {
    private GraphStationCreator graphStationCreator = new GraphStationCreator();

    @Test
    public void shouldBeZeroCostWhenUnder20Minutes() {
        assertTrue(0.0 == graphStationCreator.calculateCost(1199));
    }

    @Test
    public void shouldBeOneCost20Minutes() {
        assertTrue(1.0 == graphStationCreator.calculateCost(1200));
    }

    @Test
    public void shouldBeOneCostWhen59MinutesAnd59Seconds() {
        assertTrue(1.0 == graphStationCreator.calculateCost(3599));
    }


}
