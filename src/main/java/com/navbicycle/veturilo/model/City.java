package com.navbicycle.veturilo.model;

/**
 * Created by marcinstepnowski on 16.12.2015.
 */
import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class City {

    @XmlAttribute(name = "uid")
    private int id;

    @XmlAttribute(name = "lat")
    private float lattitude;

    @XmlAttribute(name = "lng")
    private float longtitude;

    @XmlAttribute(name = "zoom")
    private float zoom;

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "place")
    private List<Place> places;

    public float getLattitude() {
        return lattitude;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public String getName() {
        return name;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public int getId() {
        return id;
    }

    public float getZoom() {
        return zoom;
    }
}
