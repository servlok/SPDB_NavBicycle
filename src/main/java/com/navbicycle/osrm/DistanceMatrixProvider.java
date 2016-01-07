package com.navbicycle.osrm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navbicycle.osrm.model.Coordinate;
import com.navbicycle.osrm.model.RouterResponse;
import com.oracle.tools.packager.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by marcinstepnowski on 07.01.2016.
 */
public class DistanceMatrixProvider {

    public RouterResponse downloadMatrix(List<Coordinate> coordinates) {
        URI uri = QueryConstructor.uriWithCoordinates(coordinates);
        String string;

        try {
            URL url = uri.toURL();
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            int numCharsRead;
            char[] charArray = new char[1024];
            StringBuffer sb = new StringBuffer();
            while ((numCharsRead = isr.read(charArray)) > 0) {
                sb.append(charArray, 0, numCharsRead);
            }
            string = sb.toString();


        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {
            return null;
        }

        return ResponseReader.read(string);
    }
}
