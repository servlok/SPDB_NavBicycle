package com.navbicycle.verturilo.db;

import com.navbicycle.domain.Point;
import com.navbicycle.domain.VeturiloStation;
import com.navbicycle.veturilo.db.VeturiloStationRepository;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

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

        System.out.println(stations);
    }

    @Test
    public void shoudlReturnListOfVerturiloStationsBetweenTwoPointsWithOffset() throws SQLException {
        List<VeturiloStation> stations = veturiloStationRepository.findAllInRectangleBetweenPoints(start, end, 2000);

        System.out.println(stations);
    }

    @Test
    public void shoudlReturnListOfVerturiloStationsBetweenTwoPointsWithOffsetFraction() throws SQLException {
        List<VeturiloStation> stations = veturiloStationRepository.findAllInRectangleBetweenPoints(start, end, 0.1f);

        System.out.println(stations);
    }
}
