package com.navbicycle.service;

import com.navbicycle.domain.Point;
import com.navbicycle.domain.Route;
import com.navbicycle.domain.VeturiloStation;
import com.navbicycle.veturilo.db.VeturiloStationRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by servlok on 18.01.16.
 */
public class RouteFinder {

    private static final float OFFSET_FRACTION = 0.1f;
    private VeturiloStationRepository veturiloStationRepository = new VeturiloStationRepository();

    public Route findRouteBetweenPoint(Point start, Point end) throws SQLException {
        VeturiloStation startingStation = veturiloStationRepository.findNereast(start.getLat(), start.getLng());
        VeturiloStation endingStation = veturiloStationRepository.findNereast(end.getLat(), end.getLng());

        List<VeturiloStation> availableStations = veturiloStationRepository.findAllInRectangleBetweenPoints(
                startingStation, endingStation, OFFSET_FRACTION
        );


        return null;
    }
}
