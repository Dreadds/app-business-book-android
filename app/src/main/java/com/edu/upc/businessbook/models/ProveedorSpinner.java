package com.edu.upc.businessbook.models;

public class ProveedorSpinner {

    private int proveedorId;
    private String name;

    public ProveedorSpinner() {
    }

    public ProveedorSpinner(int proveedorId, String name) {
        this.proveedorId = proveedorId;
        this.name = name;
    }

    public int getProveedorId() {
        return proveedorId;
    }

    public ProveedorSpinner setProveedorId(int proveedorId) {
        this.proveedorId = proveedorId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProveedorSpinner setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return name;
    }
}
