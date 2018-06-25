package com.edu.upc.businessbook.models;

public class SaleDetail {
    public int SaleDetailId;
    public int ProductId;
    public int Quantity;
    public float UnitPrice;
    public float PriceSubTotal;
    public int ProductPos;

    public SaleDetail() {
    }


    public SaleDetail(int saleDetailId, int productId, int quantity, float unitPrice, float priceSubTotal, int productPos) {
        SaleDetailId = saleDetailId;
        ProductId = productId;
        Quantity = quantity;
        UnitPrice = unitPrice;
        PriceSubTotal = priceSubTotal;
        ProductPos = productPos;
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

    public int getProductPos() {
        return ProductPos;
    }

    public SaleDetail setProductPos(int productPos) {
        ProductPos = productPos;
        return this;
    }
}
