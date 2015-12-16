package com.navbicycle.veturilo.model;

/**
 * Created by marcinstepnowski on 16.12.2015.
 */

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Place {

    @XmlAttribute(name = "uid")
    private int id;

    @XmlAttribute(name = "lat")
    private float lattitude;

    @XmlAttribute(name = "lng")
    private float longtitude;

    @XmlAttribute(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public float getLattitude() {
        return lattitude;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public String getName() {
        return name;
    }
}
