package com.navbicycle.graphStation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by servlok on 19.01.16.
 */
public class GraphStationCreatorTest {
    private GraphStationCreator graphStationCreator = new GraphStationCreator();

    @Test
    public void shouldBe0CostWhenUnder20Minutes() {
        assertEquals(0.0, graphStationCreator.calculateCost(1199), 0.0);
    }

    @Test
    public void shouldBe0Cost20Minutes() {
        assertEquals(0.0, graphStationCreator.calculateCost(1200), 0.0);
    }

    @Test
    public void shouldBe1Cost20MinutesAndOneSecond() {
        assertEquals(1.0, graphStationCreator.calculateCost(1200 + 1), 0.0);
    }


    @Test
    public void shouldBe1CostWhen60Minutes() {
        assertEquals(1.0, graphStationCreator.calculateCost(3600), 0.0);
    }

    @Test
    public void shouldBe4CostWhen60MinutesAndOneSecond() {
        assertEquals(4.0, graphStationCreator.calculateCost(3600 + 1), 0.0);
    }

    @Test
    public void shouldBe4CostWhen2Hours() {
        assertEquals(4.0, graphStationCreator.calculateCost(3600 * 2), 0.0);
    }

    @Test
    public void shouldBe9CostWhen2HoursAndOneSecond() {
        assertEquals(9.0, graphStationCreator.calculateCost(3600 * 2 + 1), 0.0);
    }

    @Test
    public void shouldBe9CostWhen3Hours() {
        assertEquals(9.0, graphStationCreator.calculateCost(3600 * 3), 0.0);
    }

    @Test
    public void shouldBe16CostWhen3HoursAndOneSecond() {
        assertEquals(16.0, graphStationCreator.calculateCost(3600 * 3 + 1), 0.0);
    }

    @Test
    public void shouldBe16CostWhen4Hours() {
        assertEquals(16.0, graphStationCreator.calculateCost(3600 * 4), 0.0);
    }

    @Test
    public void shouldBe9CostWhen6Hours() {
        assertEquals(16 + 2 * 7.0, graphStationCreator.calculateCost(3600 * 6), 0.0);
    }


}
