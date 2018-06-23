package com.edu.upc.businessbook.viewcontrollers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.LocalSpinner;
import com.edu.upc.businessbook.models.ProductSpinner;
import com.edu.upc.businessbook.models.SaleDetail;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.PrivateCredentialPermission;

public class SaleDetailAdapter
        extends RecyclerView.Adapter<SaleDetailAdapter.ViewHolder> {

    public List<SaleDetail> saleDetails;
    public ArrayAdapter<ProductSpinner> adapter;

    String[] quantitys;
    String[] products;
    String[] punits;
    String[] sptotals;

    public SaleDetailAdapter(List<SaleDetail> saleDetails, ArrayAdapter<ProductSpinner> adapter) {
        this.saleDetails = saleDetails;
        this.adapter = adapter;
        quantitys = new String[saleDetails.size()];
    }

    public SaleDetailAdapter(List<SaleDetail> saleDetails) {
        this.saleDetails = saleDetails;
        quantitys = new String[saleDetails.size()];
        products = new String[saleDetails.size()];
        punits = new String[saleDetails.size()];
        quantitys = new String[saleDetails.size()];
    }

    public String[] getQuantitys() { return quantitys; }
    public String[] getProducts() { return products; }
    public String[] getPunits() { return punits; }
    public String[] getSptotals() { return sptotals; }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SaleDetailAdapter.
                ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_sale_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.updateViews(saleDetails.get(position));
        holder.InsertSpinnerProduct(adapter);
    }

    @Override
    public int getItemCount() {
        return saleDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private EditText quantityEditText;
        private Spinner productSpinner;
        private TextView unitPriceTextView;
        private TextView subPriceTextView;

        public ViewHolder (View view) {
            super(view);
            quantityEditText = (EditText) view.findViewById(R.id.quantity_editText);
            productSpinner = (Spinner) view.findViewById((R.id.product_spinner));
            unitPriceTextView = (TextView) view.findViewById(R.id.unitPrice_textView);
            subPriceTextView = (TextView) view.findViewById(R.id.subPrice_textView);

            quantityEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    quantitys[getAdapterPosition()] = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    /*Adapter adapterProduct = productSpinner.getAdapter();
                    int posProduct = productSpinner.getSelectedItemPosition();
                    ProductSpinner ls = (ProductSpinner) adapterLocal.getItem(posProduct);
                    addSaleModel.localId = ls.getLocalId();*/
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        public void updateViews(final SaleDetail saleDetail){
            quantityEditText.setText(Integer.toString(saleDetail.Quantity));
            unitPriceTextView.setText(Float.toString(saleDetail.UnitPrice));
            subPriceTextView.setText(Float.toString(saleDetail.PriceSubTotal));
        }

        public void InsertSpinnerProduct(ArrayAdapter<ProductSpinner> adapter){ productSpinner.setAdapter(adapter); }
    }
}
