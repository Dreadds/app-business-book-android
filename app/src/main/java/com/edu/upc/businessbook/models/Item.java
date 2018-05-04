package com.edu.upc.businessbook.models;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

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

    public int getThumbnail() {
        return thumbnail;
    }

    public Item setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("name", getName());
        bundle.putString("description", getDescription());
        bundle.putInt("thumbnail", getThumbnail());
        return bundle;
    }

    public static  class Builder{
        private Item item;
        private List<Item> items;

        public Builder(){
            this.item = new Item();
            this.items = new ArrayList<>();
        }

        public Builder (Item item) {
            this.item = item;
        }

        public Builder (List<Item> items) {
            this.items = items;
        }
        public Item build(){
            return item;
        }

        public List<Item> buildAll() {
            return items;
        }

        public static Builder from(Bundle bundle){
            return new Builder(new Item(
                    bundle.getString("name"),
                    bundle.getString("description"),
                    bundle.getInt("thumbnail")

            ));
        }
    }
}
