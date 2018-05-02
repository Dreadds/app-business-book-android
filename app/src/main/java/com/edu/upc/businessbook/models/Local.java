package com.edu.upc.businessbook.models;

import android.os.Bundle;

public class Local {
    private String localId;
    private String name;
    private String address;
    private int thumbnail;

    public Local() {
    }

    public Local(String localId, String name, String address) {
        this.localId = localId;
        this.name = name;
        this.address = address;
    }

    public Local(String name, String address, int thumbnail) {
        this.name = name;
        this.address = address;
        this.thumbnail = thumbnail;
    }


    public String getLocalId() {
        return localId;
    }

    public Local setLocalId(String localId) {
        this.localId = localId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Local setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Local setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public Local setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("localId", getLocalId());
        bundle.putString("name", getName());
        bundle.putString("address", getAddress());
        return bundle;
    }
}
