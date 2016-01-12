package com.navbicycle.verturilo.db;

import com.navbicycle.veturilo.db.PlaceRepository;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by servlok on 11.01.16.
 */
public class PlaceRepositoryTest {

    @Test
    public void shouldReturnListOfVerturiloStations() throws SQLException {
        PlaceRepository placeRepository = new PlaceRepository();
        assertEquals(216, placeRepository.findAll().size());
    }
}
