package com.edu.upc.businessbook.models;

public class Product {
    private String prodcutId;
    private String name;
    private String unitPrice;
    private String state;

    public Product(String prodcutId, String name, String unitPrice, String state) {
        this.prodcutId = prodcutId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.state = state;
    }

    public Product() {
    }

    public String getProdcutId() {
        return prodcutId;
    }

    public Product setProdcutId(String prodcutId) {
        this.prodcutId = prodcutId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public Product setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public String getState() {
        return state;
    }

    public Product setState(String state) {
        this.state = state;
        return this;
    }
}
