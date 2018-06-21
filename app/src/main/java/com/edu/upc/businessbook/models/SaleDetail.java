package com.edu.upc.businessbook.models;

public class SaleDetail {
    public int SaleDetailId;
    public int ProductId;
    public int Quantity;
    public float UnitPrice;
    public float PriceSubTotal;

    public SaleDetail() {
    }

    public SaleDetail(int productId, int quantity, float unitPrice, float priceSubTotal) {
        ProductId = productId;
        Quantity = quantity;
        UnitPrice = unitPrice;
        PriceSubTotal = priceSubTotal;
    }

    public int getSaleDetailId() {
        return SaleDetailId;
    }

    public SaleDetail setSaleDetailId(int saleDetailId) {
        SaleDetailId = saleDetailId;
        return this;
    }

    public int getProductId() {
        return ProductId;
    }

    public SaleDetail setProductId(int productId) {
        ProductId = productId;
        return this;
    }

    public int getQuantity() {
        return Quantity;
    }

    public SaleDetail setQuantity(int quantity) {
        Quantity = quantity;
        return this;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public SaleDetail setUnitPrice(float unitPrice) {
        UnitPrice = unitPrice;
        return this;
    }

    public float getPriceSubTotal() {
        return PriceSubTotal;
    }

    public SaleDetail setPriceSubTotal(float priceSubTotal) {
        PriceSubTotal = priceSubTotal;
        return this;
    }
}
