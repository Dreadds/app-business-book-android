package com.edu.upc.businessbook.models;

import android.os.Bundle;

public class Item {
    private String name;
    private String description;
    private int thumbnail;
    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public Item() {

    }

    public Item(String name, String description, int thumbnail) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
    }

    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("name", getName());
        bundle.putString("description", getDescription());
        return bundle;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public Item setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }
}
