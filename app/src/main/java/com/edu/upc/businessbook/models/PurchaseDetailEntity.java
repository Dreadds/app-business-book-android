package com.edu.upc.businessbook.models;

public class PurchaseDetailEntity {
    public int productId;
    public int quantity;
    public float unitPrice;
    public float priceSubTotal;

    public PurchaseDetailEntity(int productId, int quantity, float unitPrice, float priceSubTotal) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.priceSubTotal = priceSubTotal;
    }

    public PurchaseDetailEntity() {
    }

    public int getProductId() {
        return productId;
    }

    public PurchaseDetailEntity setProductId(int productId) {
        this.productId = productId;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public PurchaseDetailEntity setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public PurchaseDetailEntity setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public float getPriceSubTotal() {
        return priceSubTotal;
    }

    public PurchaseDetailEntity setPriceSubTotal(float priceSubTotal) {
        this.priceSubTotal = priceSubTotal;
        return this;
    }
}
