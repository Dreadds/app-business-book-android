package com.edu.upc.businessbook.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Purchase {
    public int PurchaseId;
    public String CodeGuide;
    public String LocalName;
    public float PriceTotal;
    public String ProviderName;
    public String State;

    public Purchase(int purchaseId, String codeGuide, String localName, float priceTotal, String providerName, String state) {
        PurchaseId = purchaseId;
        CodeGuide = codeGuide;
        LocalName = localName;
        PriceTotal = priceTotal;
        ProviderName = providerName;
        State = state;
    }

    public Purchase() {
    }

    public String getCodeGuide() {
        return CodeGuide;
    }

    public Purchase setCodeGuide(String codeGuide) {
        CodeGuide = codeGuide;
        return this;
    }

    public int getPurchaseId() {
        return PurchaseId;
    }

    public Purchase setPurchaseId(int purchaseId) {
        PurchaseId = purchaseId;
        return this;
    }

    public String getState() {
        return State;
    }

    public Purchase setState(String state) {
        State = state;
        return this;
    }

    public String getLocalName() {
        return LocalName;
    }

    public Purchase setLocalName(String localName) {
        LocalName = localName;
        return this;
    }

    public float getPriceTotal() {
        return PriceTotal;
    }

    public Purchase setPriceTotal(float priceTotal) {
        PriceTotal = priceTotal;
        return this;
    }

    public String getProviderName() {
        return ProviderName;
    }

    public Purchase setProviderName(String providerName) {
        ProviderName = providerName;
        return this;
    }

    //sirve para trasnferir datos de una vista otra
    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putInt("PurchaseId",getPurchaseId());
        bundle.putString("CodeGuide",getCodeGuide());
        bundle.putString("LocalName",getLocalName());
        bundle.putFloat("PriceTotal",getPriceTotal());
        bundle.putString("ProveedorName",getProviderName());
        bundle.putString("State",getState());
        return bundle;
    }

    public static class Builder{
        private Purchase purchase;
        private List<Purchase> purchases;


        public Builder(Purchase purchase) {
            this.purchase = purchase;
        }

        public Builder(List<Purchase> purchases) {
            this.purchases = purchases;
        }

        public Purchase build(){ return purchase; }
        public List<Purchase> buildAll() {return purchases;}

        public static Purchase.Builder from(Bundle bundle){
            return new Purchase.Builder(new Purchase(
                    bundle.getInt("purchaseId"),
                    bundle.getString("codeGuide"),
                    bundle.getString("localName"),
                    bundle.getFloat("priceTotal"),
                    bundle.getString("proveedorName"),
                    bundle.getString("state")
            ));
        }
        public static Purchase.Builder from(JSONObject jsonPurchase) {
            try {
                Double totalPrice = (jsonPurchase.getString("priceTotal").equals("null")) ? 0.0 : jsonPurchase.getDouble("priceTotal");

                return new Purchase.Builder(new Purchase(
                        jsonPurchase.getInt("purchaseId"),
                        jsonPurchase.getString("codeGuide"),
                        jsonPurchase.getString("localName"),
                        BigDecimal.valueOf(totalPrice).floatValue(),
                        jsonPurchase.getString("proveedorName"),
                        jsonPurchase.getString("state")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static Purchase.Builder from(JSONArray jsonPurchase) {
            List<Purchase> purchases = new ArrayList<>();
            int length = jsonPurchase.length();
            for(int i = 0; i < length; i++) {
                try {
                    purchases.add(Purchase.Builder.from(jsonPurchase.getJSONObject(i)).build());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return new Purchase.Builder(purchases);
        }
    }
}
