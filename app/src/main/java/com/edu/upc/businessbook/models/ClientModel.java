package com.edu.upc.businessbook.models;

public class ClientModel {
    private int clientId;
    private String name;

    public ClientModel() {
    }

    public ClientModel(int clientId, String name) {
        this.clientId = clientId;
        this.name = name;
    }

    public int getClientId() {
        return clientId;
    }

    public ClientModel setClientId(int clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClientModel setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return name;
    }
}
