package com.navbicycle.veturilo.db;

import com.navbicycle.sql.DBUtil;
import com.navbicycle.veturilo.model.Place;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
