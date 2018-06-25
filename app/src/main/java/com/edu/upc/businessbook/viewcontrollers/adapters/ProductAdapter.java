package com.edu.upc.businessbook.viewcontrollers.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private List<Product> products;

    public ProductAdapter(List<Product> products) {
        this.products = products;
    }

    public ProductAdapter() {
    }


    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.updateView(products.get(position));

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public List<Product> getProducts() {
        return products;
    }

    public ProductAdapter setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ANImageView logoProduct;
        TextView nameTextView, unitPriceTextView;
        CardView layoutItemProduct;
        public ViewHolder(View view) {
            super(view);
            logoProduct = (ANImageView) view.findViewById(R.id.logoProductANImageView);
            nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            unitPriceTextView = (TextView) view.findViewById(R.id.unitPriceTextView);
            layoutItemProduct = (CardView) view.findViewById(R.id.layout_itemProduct);
        }
        public void updateView(Product product){
            logoProduct.setDefaultImageResId(R.drawable.barcode);
            logoProduct.setErrorImageResId(R.drawable.barcode);
            nameTextView.setText(product.getName());
            unitPriceTextView.setText(product.getUnitPrice());
        }
    }
}
