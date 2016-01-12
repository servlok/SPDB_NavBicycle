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
}
