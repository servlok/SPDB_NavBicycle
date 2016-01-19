package com.navbicycle.service;

import com.navbicycle.domain.Point;
import com.navbicycle.domain.RoutePoints;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by servlok on 19.01.16.
 */
public class RoutePointsFinderTest {

    private RoutePointsFinder routePointsFinder = new RoutePointsFinder();

    @Test
    public void shouldTestLongDistance() throws SQLException {
        Point start = new Point(52.223461, 20.925628);
        Point end = new Point(52.319691, 21.061947);

        routePointsFinder.findRouteBetweenPoint(start, end);
    }

    @Test
    public void shouldTestShortDistance() throws SQLException {
        Point start = new Point(52.195890, 20.998152);
        Point end = new Point(52.213757, 21.025798);

        RoutePoints routePoints = routePointsFinder.findRouteBetweenPoint(start, end);

        System.out.println("STOP!");
    }

    @Test
    public void shouldTestNormalDistancePKPUrsus_SadybaBestMall() throws SQLException {
        Point start = new Point(52.196416, 20.881654);
        Point end = new Point(52.186895, 21.062509);

        RoutePoints routePoints = routePointsFinder.findRouteBetweenPoint(start, end);

        System.out.println("STOP!");
    }

}
