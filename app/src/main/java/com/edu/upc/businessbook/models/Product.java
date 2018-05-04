package com.edu.upc.businessbook.models;

import android.os.Bundle;

public class Product {
    private String productId;
    private String name;
    private String unitPrice;
    private String state;

    public Product(String productId, String name, String unitPrice, String state) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.state = state;
    }

    public Product() {
    }

    public String getProductId() {
        return productId;
    }

    public Product setProductId(String productId) {
        this.productId = productId;
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

    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("productId", getProductId());
        bundle.putString("name", getName());
        bundle.putString("unitPrice", getUnitPrice());
        bundle.putString("state", getState());
        return bundle;
    }
}
