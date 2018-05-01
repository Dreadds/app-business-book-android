package com.edu.upc.businessbook.models;

public class Local {
    private String localId;
    private String name;
    private String address;

    public Local() {
    }

    public Local(String localId, String name, String address) {
        this.localId = localId;
        this.name = name;
        this.address = address;
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
}
