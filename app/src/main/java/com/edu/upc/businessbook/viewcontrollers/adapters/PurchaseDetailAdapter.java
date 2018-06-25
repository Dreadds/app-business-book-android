package com.edu.upc.businessbook.viewcontrollers.adapters;

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

import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.ProductSpinner;
import com.edu.upc.businessbook.models.PurchaseDetail;

import java.util.List;

public class PurchaseDetailAdapter extends RecyclerView.Adapter<PurchaseDetailAdapter.ViewHolder> {

    public List<PurchaseDetail> purchaseDetails;
    public ArrayAdapter<ProductSpinner> adapter;

    String[] quantitys;
    String[] products;
    String[] productsPos;
    String[] punits;
    String[] sptotals;

    public PurchaseDetailAdapter(List<PurchaseDetail> purchaseDetails, ArrayAdapter<ProductSpinner> adapter) {
        this.purchaseDetails = purchaseDetails;
        this.adapter = adapter;
        quantitys = new String[purchaseDetails.size()];
        products = new String[purchaseDetails.size()];
        punits = new String[purchaseDetails.size()];
        sptotals = new String[purchaseDetails.size()];
        productsPos = new String[purchaseDetails.size()];
    }

    public PurchaseDetailAdapter(List<PurchaseDetail> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
        quantitys = new String[purchaseDetails.size()];
        products = new String[purchaseDetails.size()];
        punits = new String[purchaseDetails.size()];
        sptotals = new String[purchaseDetails.size()];
        productsPos = new String[purchaseDetails.size()];
    }

    public String[] getQuantitys() { return quantitys; }
    public String[] getProducts() { return products; }
    public String[] getPunits() { return punits; }
    public String[] getSptotals() { return sptotals; }
    public String[] getProductsPos() { return productsPos; }

    @Override
    public PurchaseDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PurchaseDetailAdapter.
                ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_purchase_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.InsertSpinnerProduct(adapter);
        holder.updateViews(purchaseDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return purchaseDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private EditText quantityEditText;
        private Spinner productSpinner;
        private TextView unitPriceTextView;
        private TextView subPriceTextView;
        private ImageButton deleteImageView;

        public ViewHolder (View view) {
            super(view);
            quantityEditText = (EditText) view.findViewById(R.id.quantity_editTextPurchase);
            productSpinner = (Spinner) view.findViewById((R.id.product_spinnerPurchase));
            unitPriceTextView = (TextView) view.findViewById(R.id.unitPrice_textViewPurchase);
            subPriceTextView = (TextView) view.findViewById(R.id.subPrice_textViewPurchase);
            deleteImageView = (ImageButton) view.findViewById(R.id.deleteSD_imageButtonPurchase);


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


        public void updateViews(final PurchaseDetail purchaseDetail){
            quantityEditText.setText(Integer.toString(purchaseDetail.Quantity));
            productSpinner.setSelection(purchaseDetail.ProductPos);
            unitPriceTextView.setText(Float.toString(purchaseDetail.UnitPrice));
            subPriceTextView.setText(Float.toString(purchaseDetail.PriceSubTotal));
        }

        public void InsertSpinnerProduct(ArrayAdapter<ProductSpinner> adapter){
            productSpinner.setAdapter(adapter);
        }
    }
}
