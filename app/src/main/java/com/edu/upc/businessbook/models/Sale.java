package com.edu.upc.businessbook.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Sale {

    int SaleId;
    String DateCreation;
    String CodeGuide;
    String LocalName;
    float PriceTotal;
    String EmployeeName;
    String ClientName;
    String State;
    String StateDelivery;

    public Sale() { }


    public Sale(int saleId, String dateCreation, String codeGuide, String localName, float priceTotal, String employeeName, String clientName, String state, String stateDelivery) {
        SaleId = saleId;
        DateCreation = dateCreation;
        CodeGuide = codeGuide;
        LocalName = localName;
        PriceTotal = priceTotal;
        EmployeeName = employeeName;
        ClientName = clientName;
        State = state;
        StateDelivery = stateDelivery;
    }

    public int getSaleId() {
        return SaleId;
    }

    public Sale setSaleId(int saleId) {
        SaleId = saleId;
        return this;
    }

    public String getDateCreation() {
        return DateCreation;
    }

    public Sale setDateCreation(String dateCreation) {
        DateCreation = dateCreation;
        return this;
    }

    public String getCodeGuide() {
        return CodeGuide;
    }

    public Sale setCodeGuide(String codeGuide) {
        CodeGuide = codeGuide;
        return this;
    }

    public String getLocalName() {
        return LocalName;
    }

    public Sale setLocalName(String localName) {
        LocalName = localName;
        return this;
    }

    public float getPriceTotal() {
        return PriceTotal;
    }

    public Sale setPriceTotal(float priceTotal) {
        PriceTotal = priceTotal;
        return this;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public Sale setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
        return this;
    }

    public String getClientName() {
        return ClientName;
    }

    public Sale setClientName(String clientName) {
        ClientName = clientName;
        return this;
    }

    public String getState() {
        return State;
    }

    public Sale setState(String state) {
        State = state;
        return this;
    }

    public String getStateDelivery() {
        return StateDelivery;
    }

    public Sale setStateDelivery(String stateDelivery) {
        StateDelivery = stateDelivery;
        return this;
    }

    //sirve para trasnferir datos de una vista otra
    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putInt("SaleId",getSaleId());
        bundle.putString("DateCreation",getDateCreation());
        bundle.putString("CodeGuide",getCodeGuide());
        bundle.putString("LocalName",getLocalName());
        bundle.putFloat("PriceTotal",getPriceTotal());
        bundle.putString("EmployeeName",getEmployeeName());
        bundle.putString("ClientName",getClientName());
        bundle.putString("State",getState());
        bundle.putString("StateDelivery",getStateDelivery());
        return bundle;
    }

    public static class Builder{
        private Sale sale;
        private List<Sale> sales;

        public Builder() {
            this.sale = new Sale();
            this.sales = new ArrayList<>();
        }

        public Builder(Sale sale) { this.sale = sale; }
        public Builder(List<Sale> sales) {
            this.sales = sales;
        }


        public Sale build(){ return sale; }
        public List<Sale> buildAll() {return sales;}


        public static Sale.Builder from(Bundle bundle){
            return new Sale.Builder(new Sale(
                    bundle.getInt("saleId"),
                    bundle.getString("dateCreation"),
                    bundle.getString("codeGuide"),
                    bundle.getString("localName"),
                    bundle.getFloat("priceTotal"),
                    bundle.getString("employeeName"),
                    bundle.getString("clientName"),
                    bundle.getString("state"),
                    bundle.getString("stateDelivery")
            ));
        }
        public static Builder from(JSONObject jsonSale) {
            try {
                Double totalPrice = (jsonSale.getString("priceTotal").equals("null")) ? 0.0 : jsonSale.getDouble("priceTotal");

                return new Builder(new Sale(
                        jsonSale.getInt("saleId"),
                        jsonSale.getString("dateCreation"),
                        jsonSale.getString("codeGuide"),
                        jsonSale.getString("localName"),
                        BigDecimal.valueOf(totalPrice).floatValue(),
                        jsonSale.getString("employeeName"),
                        jsonSale.getString("clientName"),
                        jsonSale.getString("state"),
                        jsonSale.getString("stateDelivery")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static Sale.Builder from(JSONArray jsonSale) {
            List<Sale> sales = new ArrayList<>();
            int length = jsonSale.length();
            for(int i = 0; i < length; i++) {
                try {
                    sales.add(Sale.Builder.from(jsonSale.getJSONObject(i)).build());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return new Sale.Builder(sales);
        }
    }
}
