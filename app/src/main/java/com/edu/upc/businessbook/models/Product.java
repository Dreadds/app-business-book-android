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
}
