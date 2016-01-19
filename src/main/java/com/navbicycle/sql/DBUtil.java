package com.navbicycle.sql;

/**
 * Created by servlok on 11.01.16.
 */
public final class DBUtil {
    public static final String DB_DRIVER = "org.postgresql.Driver";
    public static final String DB_CONNECTION = "jdbc:postgresql://localhost/nav_bicycle";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "postgres";

    public static final String FIND_ALL_PLACE_SQL = "select uid,name,ST_X(geom),ST_Y(geom) from verturilo_stations";
    public static final String FIND_NEAREST_PLACE_SQL = "select uid,name,ST_X(geom),ST_Y(geom) " +
            "from verturilo_stations " +
            "order by geom <-> ST_GeomFromText(?, 4326) " +
            "limit 1";

    public static final String FIND_ALL_BETWEEN_POINTS =
            "select uid,name,ST_X(geom),ST_Y(geom) " +
                    "from verturilo_stations " +
                    "where ST_Covers(ST_MakeEnvelope(?, ?, ?, ?, 4326),geom)";

    //potrzeba takiej metody
    //http://www.postgresql.org/message-id/CAH7T-apf-tXft3=y91AwnybTNcvcsUtMdP1DAmtbDj+jgwZpJg@mail.gmail.com
    public static String formatPoint(double lon, double lat) {
        return "POINT(" + lon + " " + lat + ")";
    }

}
