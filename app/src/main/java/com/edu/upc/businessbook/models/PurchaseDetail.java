package com.edu.upc.businessbook.models;

public class PurchaseDetail {
    public int PurchaseDetailId;
    public int ProductId;
    public int Quantity;
    public float UnitPrice;
    public float PriceSubTotal;
    public int ProductPos;

    public PurchaseDetail(int purchaseDetailId, int productId, int quantity, float unitPrice, float priceSubTotal, int productPos) {
        PurchaseDetailId = purchaseDetailId;
        ProductId = productId;
        Quantity = quantity;
        UnitPrice = unitPrice;
        PriceSubTotal = priceSubTotal;
        ProductPos = productPos;
    }

    public PurchaseDetail() {
    }

    public int getPurchaseDetailId() {
        return PurchaseDetailId;
    }

    public PurchaseDetail setPurchaseDetailId(int purchaseDetailId) {
        PurchaseDetailId = purchaseDetailId;
        return this;
    }

    public int getProductId() {
        return ProductId;
    }

    public PurchaseDetail setProductId(int productId) {
        ProductId = productId;
        return this;
    }

    public int getQuantity() {
        return Quantity;
    }

    public PurchaseDetail setQuantity(int quantity) {
        Quantity = quantity;
        return this;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public PurchaseDetail setUnitPrice(float unitPrice) {
        UnitPrice = unitPrice;
        return this;
    }

    public float getPriceSubTotal() {
        return PriceSubTotal;
    }

    public PurchaseDetail setPriceSubTotal(float priceSubTotal) {
        PriceSubTotal = priceSubTotal;
        return this;
    }

    public int getProductPos() {
        return ProductPos;
    }

    public PurchaseDetail setProductPos(int productPos) {
        ProductPos = productPos;
        return this;
    }
}
