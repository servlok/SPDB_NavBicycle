package com.navbicycle.veturilo.model;

/**
 * Created by marcinstepnowski on 16.12.2015.
 */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

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

    public void setId(int id) {
        this.id = id;
    }

    public float getLattitude() {
        return lattitude;
    }

    public void setLattitude(float lattitude) {
        this.lattitude = lattitude;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(float longtitude) {
        this.longtitude = longtitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;

        Place place = (Place) o;

        if (id != place.id) return false;
        if (Float.compare(place.lattitude, lattitude) != 0) return false;
        if (Float.compare(place.longtitude, longtitude) != 0) return false;
        return name.equals(place.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (lattitude != +0.0f ? Float.floatToIntBits(lattitude) : 0);
        result = 31 * result + (longtitude != +0.0f ? Float.floatToIntBits(longtitude) : 0);
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", lattitude=" + lattitude +
                ", longtitude=" + longtitude +
                ", name='" + name + '\'' +
                '}';
    }

}
