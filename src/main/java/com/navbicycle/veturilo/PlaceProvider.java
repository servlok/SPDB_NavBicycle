package com.navbicycle.veturilo;

import com.navbicycle.veturilo.model.Markers;
import com.navbicycle.veturilo.model.Place;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.net.URL;
import java.util.List;

/**
 * Created by marcinstepnowski on 16.12.2015.
 */
public class PlaceProvider {

    public List<Place> downloadPlaces() {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Markers.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            URL url = new URL("https://nextbike.net/maps/nextbike-official.xml?city=210");
            Markers markers = (Markers) unmarshaller.unmarshal(url);
            return markers.getCountry().getCity().getPlaces();
        } catch (Exception e) {
            return null;
        }
    }
}
