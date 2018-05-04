package com.edu.upc.businessbook.models;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Local {
    private String localId;
    private String name;
    private String address;
    private int thumbnail;

    public Local() {
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

    public static  class Builder{
        private Local local;
        private List<Local> locals;

        public Builder(){
            this.local = new Local();
            this.locals = new ArrayList<>();
        }

        public Builder (Local local) {
            this.local = local;
        }

        public Builder (List<Local> locals) {
            this.locals = locals;
        }
        public Local build(){
            return local;
        }

        public List<Local> buildAll() {
            return locals;
        }

        public static Builder from(Bundle bundle){
            return new Builder(new Local(
                    bundle.getString("name"),
                    bundle.getString("address"),
                    bundle.getInt("thumbnail")

            ));
        }
    }
}
