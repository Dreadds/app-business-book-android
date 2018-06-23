package com.edu.upc.businessbook.models;

public class ProductSpinner {

    private int productId;
    private String name;

    public ProductSpinner() {
    }

    public ProductSpinner(int productId, String name) {
        this.productId = productId;
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public ProductSpinner setProductId(int productId) {
        this.productId = productId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductSpinner setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return name;
    }
}
