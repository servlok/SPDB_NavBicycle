package com.navbicycle.sql.importStations;

import com.navbicycle.sql.DBUtil;
import com.navbicycle.veturilo.PlaceProvider;
import com.navbicycle.veturilo.model.Place;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by servlok on 11.01.16.
 */
public class StationsToDatabaseImporter {

    private static String INSERT_STATEMENT =
            "INSERT INTO verturilo_stations(name,uid,geom) VALUES(?,?,ST_GeomFromText(?, 4326))";
    private PlaceProvider placeProvider = new PlaceProvider();

    private static String formatPoint(float lon, float lat) {
        return "POINT(" + lon + " " + lat + ")";
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(DBUtil.DB_DRIVER);

        Connection connection = null;

        connection = DriverManager.getConnection(
                DBUtil.DB_CONNECTION,
                DBUtil.DB_USER,
                DBUtil.DB_PASSWORD
        );

        if (connection != null) {
            new StationsToDatabaseImporter().run(connection);
        } else {
            throw new IllegalStateException();
        }
    }

    private void run(Connection connection) throws SQLException {
        List<Place> places = placeProvider.downloadPlaces();

        PreparedStatement statement = connection.prepareStatement(INSERT_STATEMENT);

        for (Place p : places) {
            statement.setString(1, p.getName());
            statement.setInt(2, p.getId());
            statement.setString(3, formatPoint(p.getLongtitude(), p.getLattitude()));

            statement.executeUpdate();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Wystapil blad z zamykaniem polaczenia, " +
                    "zobacz czy sie udaly INSERTy, jak nie to powtorz wywolanie skryptu");
        }
    }
}
