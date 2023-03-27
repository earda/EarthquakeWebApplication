package com.project.earthquake.earthquakewebapplication;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EarthquakeEntity {
    @JsonProperty
    private  String country;
    @JsonProperty
    private  String location;
    @JsonProperty
    private  String magnitude;
    @JsonProperty
    private String date;

    @JsonProperty
    private String time;

    public EarthquakeEntity() {
    }


    public EarthquakeEntity(String country, String location, String magnitude, String date, String time) {
        this.country = country;
        this.location = location;
        this.magnitude = magnitude;
        this.date = date;
        this.time = time;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date ;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }
}
