package com.navbicycle.veturilo.db;

import com.navbicycle.sql.DBUtil;
import com.navbicycle.veturilo.model.Place;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaceRepository {
    public List<Place> findAll() throws SQLException {
        Connection connection = DriverManager.getConnection(
                DBUtil.DB_CONNECTION,
                DBUtil.DB_USER,
                DBUtil.DB_PASSWORD
        );

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(DBUtil.FIND_ALL_PLACE_SQL);

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
