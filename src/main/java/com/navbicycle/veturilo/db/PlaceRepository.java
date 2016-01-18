package com.navbicycle.veturilo.db;

import com.navbicycle.domain.Point;
import com.navbicycle.sql.DBUtil;
import com.navbicycle.utils.CoordinatesUtil;
import com.navbicycle.veturilo.model.Place;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.max;
import static java.lang.Math.min;

public class PlaceRepository {

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(
                DBUtil.DB_CONNECTION,
                DBUtil.DB_USER,
                DBUtil.DB_PASSWORD
        );
    }

    public List<Place> findAll() throws SQLException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(DBUtil.FIND_ALL_PLACE_SQL);
        connection.close();

        return createPlacesFromResultSet(rs);
    }

    public Place findNereast(double lattitude, double longtitude) throws SQLException{
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(DBUtil.FIND_NEAREST_PLACE_SQL);
        statement.setDouble(1, lattitude);
        statement.setDouble(2, longtitude);

        ResultSet rs = statement.executeQuery();
        connection.close();   

        List<Place> placeList = createPlacesFromResultSet(rs);
        return placeList.size() > 0 ? placeList.get(0) : null;
    }

    public List<Place> findAllInRectangleBetweenPoints(Point start, Point end) throws SQLException {
        return findAllInRectangleBetweenPoints(start, end, 0);
    }

    public List<Place> findAllInRectangleBetweenPoints(Point start, Point end, int offsetInMeters) throws SQLException {
        double y_min = min(start.getLat(), end.getLat());
        double y_max = max(start.getLat(), end.getLat());
        double x_min = min(start.getLng(), end.getLng());
        double x_max = max(start.getLng(), end.getLng());

        double y_min_offset = y_min - CoordinatesUtil.calculateOffsetLat(offsetInMeters);
        double y_max_offset = y_max + CoordinatesUtil.calculateOffsetLat(offsetInMeters);
        double x_min_offset = x_min - CoordinatesUtil.calculateOffsetLng(y_min, offsetInMeters);
        double x_max_offset = x_max + CoordinatesUtil.calculateOffsetLng(y_max, offsetInMeters);

        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DBUtil.FIND_ALL_BETWEEN_POINTS);
        statement.setDouble(1, x_min_offset);
        statement.setDouble(2, y_min_offset);
        statement.setDouble(3, x_max_offset);
        statement.setDouble(4, y_max_offset);

        ResultSet rs = statement.executeQuery();
        connection.close();

        return createPlacesFromResultSet(rs);
    }

    private List<Place> createPlacesFromResultSet(ResultSet rs) throws SQLException {
        List<Place> places = new ArrayList<>();
        while (rs.next())
            places.add(createPlace(rs));

        return places;
    }

    private Place createPlace(ResultSet rs) throws SQLException {
        Place p = new Place();
        p.setName(rs.getString("name"));
        p.setId(rs.getInt("uid"));
        p.setLongtitude(rs.getFloat("st_x"));
        p.setLattitude(rs.getFloat("st_y"));
        return p;
    }
}
