package com.navbicycle.verturilo.db;

import com.navbicycle.domain.Point;
import com.navbicycle.domain.VeturiloStation;
import com.navbicycle.veturilo.db.VeturiloStationRepository;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by servlok on 11.01.16.
 */
public class VeturiloStationRepositoryTest {

    private VeturiloStationRepository veturiloStationRepository = new VeturiloStationRepository();
    private Point start = new Point(52.195890, 20.998152);
    private Point end = new Point(52.213757, 21.025798);

    @Test
    public void shouldReturnListOfVerturiloStations() throws SQLException {
        assertEquals(216, veturiloStationRepository.findAll().size());
    }

    @Test
    public void shoudlReturnListOfVerturiloStationsBetweenTwoPoints() throws SQLException {
        List<VeturiloStation> stations = veturiloStationRepository.findAllInRectangleBetweenPoints(start, end);

    }

    @Test
    public void shoudlReturnListOfVerturiloStationsBetweenTwoPointsWithOffset() throws SQLException {
        List<VeturiloStation> stations = veturiloStationRepository.findAllInRectangleBetweenPoints(start, end, 2000);

    }

    @Test
    public void shoudlReturnListOfVerturiloStationsBetweenTwoPointsWithOffsetFraction() throws SQLException {
        List<VeturiloStation> stations = veturiloStationRepository.findAllInRectangleBetweenPoints(start, end, 0.1f);

    }

    @Test
    public void shoudlReturnListOfVerturiloStationsBetweenTwoPointsWithOffsetFraction2() throws SQLException {
        VeturiloStation startingStation = veturiloStationRepository.findNereast(start.getLat(), start.getLng());
        VeturiloStation endingStation = veturiloStationRepository.findNereast(end.getLat(), end.getLng());

        List<VeturiloStation> stations = veturiloStationRepository.findAllInRectangleBetweenPoints(startingStation, endingStation, 0.1f);

        assertTrue(stations.contains(startingStation));
        assertTrue(stations.contains(endingStation));
    }

    @Test
    public void shoudlReturnListOfVerturiloStationsBetweenTwoPoints2() throws SQLException {
        VeturiloStation startingStation = veturiloStationRepository.findNereast(start.getLat(), start.getLng());
        VeturiloStation endingStation = veturiloStationRepository.findNereast(end.getLat(), end.getLng());

        List<VeturiloStation> stations = veturiloStationRepository.findAllInRectangleBetweenPoints(startingStation, endingStation);

        assertTrue(stations.contains(startingStation));
        assertTrue(stations.contains(endingStation));
    }

    @Test
    public void shoudlReturnListOfVerturiloStationsBetweenTwoPointsWithOffset2() throws SQLException {
        VeturiloStation startingStation = veturiloStationRepository.findNereast(start.getLat(), start.getLng());
        VeturiloStation endingStation = veturiloStationRepository.findNereast(end.getLat(), end.getLng());

        List<VeturiloStation> stations = veturiloStationRepository.findAllInRectangleBetweenPoints(startingStation, endingStation, 1000);

        assertTrue(stations.contains(startingStation));
        assertTrue(stations.contains(endingStation));
    }
}

