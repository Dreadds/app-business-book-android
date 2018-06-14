package com.edu.upc.businessbook.viewcontrollers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class SaleDetailAdapter
        extends RecyclerView.Adapter<SaleDetailAdapter.ViewHolder> {

    public List<SaleDetail> saleDetails;
    public ArrayAdapter<ProductSpinner> adapter;

    public SaleDetailAdapter(List<SaleDetail> saleDetails, ArrayAdapter<ProductSpinner> adapter) {
        this.saleDetails = saleDetails;
        this.adapter = adapter;
    }

    public SaleDetailAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SaleDetailAdapter.
                ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_sale_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.updateViews(saleDetails.get(position), adapter);
    }

    @Override
    public int getItemCount() {
        return saleDetails.size();
    }

    public List<SaleDetail> getSaleDetails() {
        return saleDetails;
    }

    public SaleDetailAdapter setSaleDetails(List<SaleDetail> saleDetails) {
        this.saleDetails = saleDetails;
        return this;
    }

    public ArrayAdapter<ProductSpinner> getAdapter() {
        return adapter;
    }

    public void setAdapter(ArrayAdapter<ProductSpinner> adapter) {
        this.adapter = adapter;
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
        }

        public void updateViews(final SaleDetail saleDetail, ArrayAdapter<ProductSpinner> adapter){
            unitPriceTextView.setText(Float.toString(saleDetail.getUnitPrice()));
            subPriceTextView.setText(Float.toString(saleDetail.getPriceSubTotal()));
            productSpinner.setAdapter(adapter);
        }
    }
}
