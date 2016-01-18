package com.navbicycle.utils;

/**
 * Created by servlok on 18.01.16.
 */
//http://gis.stackexchange.com/questions/2951/algorithm-for-offsetting-a-latitude-longitude-by-some-amount-of-meters
public final class CoordinatesUtil {

    private static int RADIUS = 6378137;

    public static double calculateOffsetLat(int offsetInMeters) {
        return (double) offsetInMeters / RADIUS * 180 / Math.PI;
    }

    public static double calculateOffsetLng(double lat, int offsetInMeters) {
        return (double) offsetInMeters / (RADIUS * Math.cos(Math.PI * lat / 180)) * 180 / Math.PI;
    }
}
