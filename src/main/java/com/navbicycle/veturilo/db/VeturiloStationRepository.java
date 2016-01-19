package com.navbicycle.veturilo.db;

import com.navbicycle.domain.Point;
import com.navbicycle.domain.VeturiloStation;
import com.navbicycle.sql.DBUtil;
import com.navbicycle.utils.CoordinatesUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.Double.max;
import static java.lang.Math.min;

public class VeturiloStationRepository {

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(
                DBUtil.DB_CONNECTION,
                DBUtil.DB_USER,
                DBUtil.DB_PASSWORD
        );
    }

    private Connection getDebugConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", DBUtil.DB_USER);
        props.setProperty("password", DBUtil.DB_PASSWORD);
        props.setProperty("loglevel", String.valueOf(org.postgresql.Driver.DEBUG));


        return DriverManager.getConnection(
                DBUtil.DB_CONNECTION,
                props
        );
    }

    public List<VeturiloStation> findAll() throws SQLException {
        Connection connection = getConnection();

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery(DBUtil.FIND_ALL_PLACE_SQL);
        connection.close();

        return createPlacesFromResultSet(rs);
    }

    public VeturiloStation findNereast(double lattitude, double longtitude) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(DBUtil.FIND_NEAREST_PLACE_SQL);
        statement.setString(1, DBUtil.formatPoint(longtitude, lattitude));

        ResultSet rs = statement.executeQuery();
        connection.close();

        List<VeturiloStation> stations = createPlacesFromResultSet(rs);
        return stations.size() > 0 ? stations.get(0) : null;
    }

    public List<VeturiloStation> findAllInRectangleBetweenPoints(Point start, Point end) throws SQLException {
        Vertices v = calculateMinsMaxs(start, end);
        return executeQueryFindBetweenPointsAndMapData(v);
    }

    public List<VeturiloStation> findAllInRectangleBetweenPoints(Point start, Point end, float offsetFraction) throws SQLException {
        Vertices v = calculateMinsMaxs(start, end);

        Vertices vOffset = new Vertices();

        double distanceBetweenY = v.y_max - v.y_min;
        double distanceBetweenX = v.x_max - v.x_min;

        vOffset.y_min = v.y_min - offsetFraction * distanceBetweenY;
        vOffset.y_max = v.y_max + offsetFraction * distanceBetweenY;
        vOffset.x_min = v.x_min - offsetFraction * distanceBetweenX;
        vOffset.x_max = v.x_max + offsetFraction * distanceBetweenX;

        return executeQueryFindBetweenPointsAndMapData(vOffset);
    }

    public List<VeturiloStation> findAllInRectangleBetweenPoints(Point start, Point end, int offsetInMeters) throws SQLException {
        Vertices v = calculateMinsMaxs(start, end);

        Vertices vOffset = new Vertices();

        vOffset.y_min = v.y_min - CoordinatesUtil.calculateOffsetLat(offsetInMeters);
        vOffset.y_max = v.y_max + CoordinatesUtil.calculateOffsetLat(offsetInMeters);
        vOffset.x_min = v.x_min - CoordinatesUtil.calculateOffsetLng(v.y_min, offsetInMeters);
        vOffset.x_max = v.x_max + CoordinatesUtil.calculateOffsetLng(v.y_max, offsetInMeters);

        return executeQueryFindBetweenPointsAndMapData(vOffset);
    }

    private List<VeturiloStation> executeQueryFindBetweenPointsAndMapData(Vertices vertices) throws SQLException {
        Connection connection = getConnection();
        ResultSet rs = prepareAndExecuteQueryFindBetweenPoints(vertices, connection);
        connection.close();

        return createPlacesFromResultSet(rs);
    }

    private Vertices calculateMinsMaxs(Point start, Point end) {
        Vertices v = new Vertices();
        v.y_min = min(start.getLat(), end.getLat());
        v.y_max = max(start.getLat(), end.getLat());
        v.x_min = min(start.getLng(), end.getLng());
        v.x_max = max(start.getLng(), end.getLng());
        return v;
    }

    private ResultSet prepareAndExecuteQueryFindBetweenPoints(Vertices v, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(DBUtil.FIND_ALL_BETWEEN_POINTS);
        statement.setDouble(1, v.x_min);
        statement.setDouble(2, v.y_min);
        statement.setDouble(3, v.x_max);
        statement.setDouble(4, v.y_max);

        return statement.executeQuery();
    }

    private List<VeturiloStation> createPlacesFromResultSet(ResultSet rs) throws SQLException {
        List<VeturiloStation> stations = new ArrayList<>();
        while (rs.next())
            stations.add(createPlace(rs));

        return stations;
    }

    private VeturiloStation createPlace(ResultSet rs) throws SQLException {
        return new VeturiloStation.Builder()
                .lat(rs.getDouble("st_y"))
                .lng(rs.getDouble("st_x"))
                .name(rs.getString("name"))
                .uid(rs.getInt("uid"))
                .build();
    }

    private class Vertices {
        double y_min;
        double y_max;
        double x_min;
        double x_max;
    }
}
