package com.edu.upc.businessbook.models;

public class SaleDetailEntity {
    public int productId;
    public int quantity;
    public float unitPrice;
    public float priceSubTotal;

    public SaleDetailEntity(int productId, int quantity, float unitPrice, float priceSubTotal) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.priceSubTotal = priceSubTotal;
    }

    public SaleDetailEntity() {
    }

    public int getProductId() {
        return productId;
    }

    public SaleDetailEntity setProductId(int productId) {
        this.productId = productId;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public SaleDetailEntity setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public SaleDetailEntity setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public float getPriceSubTotal() {
        return priceSubTotal;
    }

    public SaleDetailEntity setPriceSubTotal(float priceSubTotal) {
        this.priceSubTotal = priceSubTotal;
        return this;
    }
}
