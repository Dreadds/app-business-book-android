package com.edu.upc.businessbook.models;

public class LocalSpinner {
    private int localId;
    private String name;

    public LocalSpinner() {
    }

    public LocalSpinner(int localId, String name) {
        this.localId = localId;
        this.name = name;
    }

    public int getLocalId() {
        return localId;
    }

    public LocalSpinner setLocalId(int localId) {
        this.localId = localId;
        return this;
    }

    public String getName() {
        return name;
    }

    public LocalSpinner setName(String name) {
        this.name = name;
        return this;
    }
    @Override
    public String toString() {
        return name;
    }
}
