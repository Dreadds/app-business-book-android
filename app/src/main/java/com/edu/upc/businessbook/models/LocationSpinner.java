package com.edu.upc.businessbook.models;

public class LocationSpinner {
    private int locationid;
    private String name;

    public LocationSpinner(int locationid, String name) {
        this.locationid = locationid;
        this.name = name;
    }

    public LocationSpinner() {
    }

    public int getLocationid() {
        return locationid;
    }

    public LocationSpinner setLocationid(int locationid) {
        this.locationid = locationid;
        return this;
    }

    public String getName() {
        return name;
    }

    public LocationSpinner setName(String name) {
        this.name = name;
        return this;
    }
}
