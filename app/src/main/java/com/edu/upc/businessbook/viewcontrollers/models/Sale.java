package com.edu.upc.businessbook.viewcontrollers.models;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Sale {

    int VentaId;
    String FechaCreacion;
    String CodigoGuia;
    String NombreLocal;
    float PrecioTotal;
    String NombreCompletoTrabajador;
    String NombreCompletoCliente;
    String Estado;
    String EstadoEntrega;

    public Sale() { }

    public Sale(int VentaId, String FechaCreacion, String CodigoGuia, String NombreLocal,
                float PrecioTotal, String NombreCompletoTrabajador, String NombreCompletoCliente,
                String Estado, String EstadoEntrega) {
        this.VentaId = VentaId;
        this.FechaCreacion = FechaCreacion;
        this.CodigoGuia = CodigoGuia;
        this.NombreLocal = NombreLocal;
        this.PrecioTotal = PrecioTotal;
        this.NombreCompletoTrabajador = NombreCompletoTrabajador;
        this.NombreCompletoCliente = NombreCompletoCliente;
        this.Estado = Estado;
        this.EstadoEntrega = EstadoEntrega;

    }

    public int getVentaId() {
        return VentaId;
    }

    public Sale setVentaId(int ventaId) {
        VentaId = ventaId;
        return this;
    }

    public String getFechaCreacion() {
        return FechaCreacion;
    }

    public Sale setFechaCreacion(String fechaCreacion) {
        FechaCreacion = fechaCreacion;
        return this;
    }

    public String getCodigoGuia() {
        return CodigoGuia;
    }

    public Sale setCodigoGuia(String codigoGuia) {
        CodigoGuia = codigoGuia;
        return this;
    }

    public String getNombreLocal() {
        return NombreLocal;
    }

    public Sale setNombreLocal(String nombreLocal) {
        NombreLocal = nombreLocal;
        return this;
    }

    public float getPrecioTotal() {
        return PrecioTotal;
    }

    public Sale setPrecioTotal(float precioTotal) {
        PrecioTotal = precioTotal;
        return this;
    }

    public String getNombreCompletoTrabajador() {
        return NombreCompletoTrabajador;
    }

    public Sale setNombreCompletoTrabajador(String nombreCompletoTrabajador) {
        NombreCompletoTrabajador = nombreCompletoTrabajador;
        return this;
    }

    public String getNombreCompletoCliente() {
        return NombreCompletoCliente;
    }

    public Sale setNombreCompletoCliente(String nombreCompletoCliente) {
        NombreCompletoCliente = nombreCompletoCliente;
        return this;
    }

    public String getEstado() {
        return Estado;
    }

    public Sale setEstado(String estado) {
        Estado = estado;
        return this;
    }

    public String getEstadoEntrega() {
        return EstadoEntrega;
    }

    public Sale setEstadoEntrega(String estadoEntrega) {
        EstadoEntrega = estadoEntrega;
        return this;
    }

    //sirve para trasnferir datos de una vista otra
    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putInt("VentaId",getVentaId());
        bundle.putString("FechaCreacion",getFechaCreacion());
        bundle.putString("CodigoGuia",getCodigoGuia());
        bundle.putString("NombreLocal",getNombreLocal());
        bundle.putFloat("PrecioTotal",getPrecioTotal());
        bundle.putString("NombreCompletoTrabajador",getNombreCompletoTrabajador());
        bundle.putString("NombreCompletoCliente",getNombreCompletoCliente());
        bundle.putString("Estado",getEstado());
        bundle.putString("EstadoEntrega",getEstadoEntrega());
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
                    bundle.getInt("VentaId"),
                    bundle.getString("FechaCreacion"),
                    bundle.getString("CodigoGuia"),
                    bundle.getString("NombreLocal"),
                    bundle.getFloat("PrecioTotal"),
                    bundle.getString("NombreCompletoTrabajador"),
                    bundle.getString("NombreCompletoCliente"),
                    bundle.getString("Estado"),
                    bundle.getString("EstadoEntrega")
            ));
        }
        public static Builder from(JSONObject jsonSale) {
            try {
                Double totalPrice = (jsonSale.getString("PrecioTotal").equals("null")) ? 0.0 : jsonSale.getDouble("PrecioTotal");

                return new Builder(new Sale(
                        jsonSale.getInt("VentaId"),
                        jsonSale.getString("FechaCreacion"),
                        jsonSale.getString("CodigoGuia"),
                        jsonSale.getString("NombreLocal"),
                        BigDecimal.valueOf(totalPrice).floatValue(),
                        // TODO: Change "Trabajdor" to "Trabajador" in backend
                        jsonSale.getString("NombreCompletoTrabajdor"),
                        jsonSale.getString("NombreCompletoCliente"),
                        jsonSale.getString("Estado"),
                        jsonSale.getString("EstadoEntrega")));
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
