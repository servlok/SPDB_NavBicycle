package com.navbicycle.veturilo.model;

import javax.xml.bind.annotation.*;

/**
 * Created by marcinstepnowski on 16.12.2015.
 */

@XmlRootElement(name="markers")
@XmlAccessorType(XmlAccessType.FIELD)
public class Markers {

    @XmlElement(name = "country")
    private Country country;

    public Country getCountry() {
        return country;
    }
}
