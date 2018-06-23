package com.edu.upc.businessbook.models;

public class ClientSpinner {
    private int clientId;
    private String fullName;

    public ClientSpinner() {
    }

    public ClientSpinner(int clientId, String fullName) {
        this.clientId = clientId;
        this.fullName = fullName;
    }

    public int getClientId() {
        return clientId;
    }

    public ClientSpinner setClientId(int clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getName() {
        return fullName;
    }

    public ClientSpinner setName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Override
    public String toString() {
        return fullName;
    }
}
