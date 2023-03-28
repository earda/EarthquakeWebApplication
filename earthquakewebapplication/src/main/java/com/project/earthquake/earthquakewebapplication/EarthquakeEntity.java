package com.project.earthquake.earthquakewebapplication;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class EarthquakeEntity {
    @JsonProperty
    private  String country;

    @JsonProperty
    private  String magnitude;

    @JsonProperty
    private Date time;

    public EarthquakeEntity() {
    }


    public EarthquakeEntity(String country,String magnitude, Date time) {
        this.country = country;
        this.magnitude = magnitude;
        this.time = time;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }
}
