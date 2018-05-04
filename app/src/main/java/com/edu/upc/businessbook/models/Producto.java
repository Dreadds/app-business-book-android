package com.edu.upc.businessbook.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Producto {
    private String productoId;
    private String nombre;
    private String precioUnitario;
    private String estado;

    public Producto() {
    }

    public Producto(String productoId, String nombre, String precioUnitario, String estado) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.estado = estado;
    }

    public String getProductoId() {
        return productoId;
    }

    public Producto setProductoId(String productoId) {
        this.productoId = productoId;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Producto setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getPrecioUnitario() {
        return precioUnitario;
    }

    public Producto setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
        return this;
    }

    public String getEstado() {
        return estado;
    }

    public Producto setEstado(String estado) {
        this.estado = estado;
        return this;
    }


    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("productoId", getProductoId());
        bundle.putString("nombre", getNombre());
        bundle.putString("precioUnitario", getPrecioUnitario());
        bundle.putString("estado", getEstado());
        return bundle;
    }

    public static class Builder{
        private Producto product;
        private List<Producto> products;

        public Builder(){
            this.product = new Producto();
            this.products = new ArrayList<>();
        }

        public Builder(Producto product){
            this.product = product;
        }
        public Builder (List<Producto> products){
            this.products = products;
        }
        public Producto build(){
            return product;
        }
        public List<Producto> buildAll(){
            return products;
        }

        public static Builder from(Bundle bundle){
            return new Builder(new Producto(
                    bundle.getString("productoId"),
                    bundle.getString("nombre"),
                    bundle.getString("precioUnitario"),
                    bundle.getString("estado")
            ));
        }

        public static Builder from(JSONObject jsonProduct){
            try {
                return new Builder(new Producto(
                        jsonProduct.getString("productoId"),
                        jsonProduct.getString("nombre"),
                        jsonProduct.getString("precioUnitario"),
                        jsonProduct.getString("estado")
                ));
            } catch (JSONException e) {
                    e.printStackTrace();
            }
            return null;
        }

        public static Builder from(JSONArray jsonProduct){
            int length = jsonProduct.length();
            List<Producto> products = new ArrayList<>();
            for (int i = 0; i < length; i++){
                try {
                    products.add(Builder.from(jsonProduct.getJSONObject(i)).build());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return new Builder(products);
        }
    }


}
