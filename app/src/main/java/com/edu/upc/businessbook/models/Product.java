package com.edu.upc.businessbook.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Product{
    private String productId;
    private String name;
    private String unitPrice;
    private String state;

    public Product() {
    }

    public Product(String productId, String name, String unitPrice, String state) {
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.state = state;
    }



    public String getProductId() {
        return productId;
    }

    public Product setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public Product setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public String getState() {
        return state;
    }

    public Product setState(String state) {
        this.state = state;
        return this;
    }


    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("productId", getProductId());
        bundle.putString("name", getName());
        bundle.putString("unitPrice", getUnitPrice());
        bundle.putString("state", getState());
        return bundle;
    }

    public static class Builder{
        private Product product;
        private List<Product> products;

        public Builder(){
            this.product = new Product();
            this.products = new ArrayList<>();
        }

        public Builder(Product product){
            this.product = product;
        }
        public Builder (List<Product> products){
            this.products = products;
        }
        public Product build(){
            return product;
        }
        public List<Product> buildAll(){
            return products;
        }

        public static Builder from(Bundle bundle){
            return new Builder(new Product(
                    bundle.getString("productId"),
                    bundle.getString("name"),
                    bundle.getString("unitPrice"),
                    bundle.getString("state")
            ));
        }

        public static Builder from(JSONObject jsonProduct){
            try {
                return new Builder(new Product(
                        jsonProduct.getString("productId"),
                        jsonProduct.getString("name"),
                        jsonProduct.getString("unitPrice"),
                        jsonProduct.getString("state")
                ));
            } catch (JSONException e) {
                    e.printStackTrace();
            }
            return null;
        }

        public static Builder from(JSONArray jsonProducts){
            int length = jsonProducts.length();
            List<Product> products = new ArrayList<>();
            for (int i = 0; i < length; i++){
                try {
                    products.add(Builder.from(jsonProducts.getJSONObject(i)).build());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return new Builder(products);
        }
    }


}
