package com.edu.upc.businessbook.viewcontrollers.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.Purchase;
import com.edu.upc.businessbook.models.Sale;

import java.util.List;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ViewHolder>{
    private List<Purchase> purchases;

    public PurchaseAdapter(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public PurchaseAdapter() {
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public PurchaseAdapter setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
        return this;
    }

    @NonNull
    @Override
    public PurchaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PurchaseAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.card_purchases, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.updateViews(purchases.get(position));
    }

    @Override
    public int getItemCount() {
        return purchases.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView numberTicketTextView;
        private TextView totalPrice;
        private ImageView deleteImageView;
        private ImageView editImageView;
        private ImageView viewImageView;

        public ViewHolder (View view){
            super(view);
            numberTicketTextView = (TextView) view.findViewById(R.id.TextView_numberTicketPurchase);
            totalPrice = (TextView) view.findViewById(R.id.TextView_totalPricePurchase);
            deleteImageView = (ImageView) view.findViewById(R.id.imageButton_deletePurchase);
            editImageView = (ImageView) view.findViewById(R.id.imageButton_editPurchase);
            viewImageView = (ImageView) view.findViewById(R.id.imageButton_viewPurchase);
        }

        public void updateViews(final Purchase purchase){
            numberTicketTextView.setText(purchase.getCodeGuide());
            totalPrice.setText(Float.toString(purchase.getPriceTotal()));
            deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            editImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            viewImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }
}
