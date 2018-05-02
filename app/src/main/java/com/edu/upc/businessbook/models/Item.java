package com.edu.upc.businessbook.models;

import android.os.Bundle;

public class Item {
    private String name;
    private String description;

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

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("name", getName());
        bundle.putString("description", getDescription());
        return bundle;
    }
}
