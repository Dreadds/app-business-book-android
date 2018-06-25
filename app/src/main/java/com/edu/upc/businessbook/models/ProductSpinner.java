package com.edu.upc.businessbook.models;

public class ProductSpinner {

    private int productId;
    private String name;
    private int unitPrice;

    public ProductSpinner() {
    }

    public ProductSpinner(int productId, String name, int unitPrice) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
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

    public int getUnitPrice() {
        return unitPrice;
    }

    public ProductSpinner setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    @Override
    public String toString() {
        return name;
    }
}
