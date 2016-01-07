package com.navbicycle.osrm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navbicycle.osrm.model.RouterResponse;

import java.io.IOException;

/**
 * Created by marcinstepnowski on 07.01.2016.
 */
public class ResponseReader {

    static RouterResponse read(String string) {

        ObjectMapper mapper = new ObjectMapper();
        RouterResponse response = null;
        try {
            response = mapper.readValue(string, RouterResponse.class);
        } catch (IOException e) {
            return null;
        }
        return response;
    }
}
