package com.navbicycle.veturilo.model;

/**
 * Created by marcinstepnowski on 16.12.2015.
 */
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Country {

    @XmlAttribute(name = "lat")
    private float lattitude;

    @XmlAttribute(name = "lng")
    private float longtitude;

    @XmlAttribute(name = "zoom")
    private float zoom;

    @XmlAttribute(name = "country_name")
    private String name;

    @XmlElement(name = "city")
    private City city;

    public float getLattitude() {
        return lattitude;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public float getZoom() {
        return zoom;
    }

    public String getName() {
        return name;
    }

    public City getCity() {
        return city;
    }
}
