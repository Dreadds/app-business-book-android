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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    String[] productsPos;
    String[] punits;
    String[] sptotals;

    public SaleDetailAdapter(List<SaleDetail> saleDetails, ArrayAdapter<ProductSpinner> adapter) {
        this.saleDetails = saleDetails;
        this.adapter = adapter;
        quantitys = new String[saleDetails.size()];
        products = new String[saleDetails.size()];
        punits = new String[saleDetails.size()];
        sptotals = new String[saleDetails.size()];
        productsPos = new String[saleDetails.size()];
    }

    public SaleDetailAdapter(List<SaleDetail> saleDetails) {
        this.saleDetails = saleDetails;
        quantitys = new String[saleDetails.size()];
        products = new String[saleDetails.size()];
        punits = new String[saleDetails.size()];
        sptotals = new String[saleDetails.size()];
        productsPos = new String[saleDetails.size()];
    }

    public String[] getQuantitys() { return quantitys; }
    public String[] getProducts() { return products; }
    public String[] getPunits() { return punits; }
    public String[] getSptotals() { return sptotals; }
    public String[] getProductsPos() { return productsPos; }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SaleDetailAdapter.
                ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_sale_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.InsertSpinnerProduct(adapter);
        holder.updateViews(saleDetails.get(position));
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
        private ImageButton deleteImageView;

        public ViewHolder (View view) {
            super(view);
            quantityEditText = (EditText) view.findViewById(R.id.quantity_editText);
            productSpinner = (Spinner) view.findViewById((R.id.product_spinner));
            unitPriceTextView = (TextView) view.findViewById(R.id.unitPrice_textView);
            subPriceTextView = (TextView) view.findViewById(R.id.subPrice_textView);
            deleteImageView = (ImageButton) view.findViewById(R.id.deleteSD_imageButton);


            productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Adapter adapterProduct = productSpinner.getAdapter();
                    int posProduct = productSpinner.getSelectedItemPosition();
                    ProductSpinner ls = (ProductSpinner) adapterProduct.getItem(posProduct);

                    int pos = getAdapterPosition();
                    products[pos] = String.valueOf(ls.getProductId());
                    productsPos[pos] = String.valueOf(position);
                    punits[pos] = String.valueOf(ls.getUnitPrice());
                    if(productsPos[pos] != String.valueOf(0)) {
                        unitPriceTextView.setText(punits[pos]);
                        //incorporar las lista de saleDetail
                        String q = quantityEditText.getText().toString();
                        int subTotal = Integer.parseInt(q)*ls.getUnitPrice();
                        subPriceTextView.setText(String.valueOf(subTotal));
                        sptotals[pos] = String.valueOf(subTotal);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            quantityEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    quantitys[getAdapterPosition()] = s.toString();
                    if(quantitys[getAdapterPosition()] != String.valueOf(0)) {
                        String q = quantityEditText.getText().toString();
                        if(!new String(q).equals("")) {
                            String pu = unitPriceTextView.getText().toString();
                            if (new String(pu).equals("0.0")) {
                                pu = "0";
                            }
                            int npu = Integer.parseInt(pu);

                            int nq = Integer.parseInt(q);

                            int subTotal = npu * nq;
                            subPriceTextView.setText(String.valueOf(subTotal));
                            sptotals[getAdapterPosition()] = String.valueOf(subTotal);
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }


        public void updateViews(final SaleDetail saleDetail){
            quantityEditText.setText(Integer.toString(saleDetail.Quantity));
            productSpinner.setSelection(saleDetail.ProductPos);
            unitPriceTextView.setText(Float.toString(saleDetail.UnitPrice));
            subPriceTextView.setText(Float.toString(saleDetail.PriceSubTotal));
        }

        public void InsertSpinnerProduct(ArrayAdapter<ProductSpinner> adapter){
            productSpinner.setAdapter(adapter);
        }
    }
}
