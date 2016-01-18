package com.navbicycle.verturilo.db;

import com.navbicycle.domain.Point;
import com.navbicycle.veturilo.db.PlaceRepository;
import com.navbicycle.veturilo.model.Place;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by servlok on 11.01.16.
 */
public class PlaceRepositoryTest {

    private PlaceRepository placeRepository = new PlaceRepository();
    private Point start = new Point(52.195890, 20.998152);
    private Point end = new Point(52.213757, 21.025798);


    @Test
    public void shouldReturnListOfVerturiloStations() throws SQLException {
        assertEquals(216, placeRepository.findAll().size());
    }

    @Test
    public void shoudlReturnListOfVerturiloStationsBetweenTwoPoints() throws SQLException {
        List<Place> places = placeRepository.findAllInRectangleBetweenPoints(start, end);

        System.out.println(places);
    }

    @Test
    public void shoudlReturnListOfVerturiloStationsBetweenTwoPointsWithOffset() throws SQLException {
        List<Place> places = placeRepository.findAllInRectangleBetweenPoints(start, end, 2000);

        System.out.println(places);
    }
}
