package com.edu.upc.businessbook.viewcontrollers.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.Sale;

import java.util.List;

public class SaleAdapter
        extends RecyclerView.Adapter<SaleAdapter.ViewHolder>{

    private List<Sale> sales;

    public SaleAdapter(List<Sale> sales) {
        this.sales = sales;
    }

    public SaleAdapter() {
    }

    public List<Sale> getSales() {
        return sales;
    }

    public SaleAdapter setSales(List<Sale> sales) {
        this.sales = sales;
        return this;
    }

    @NonNull
    @Override
    public SaleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.card_sales, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SaleAdapter.ViewHolder holder, int position) {
        holder.updateViews(sales.get(position));
    }

    @Override
    public int getItemCount() {
        return sales.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView numberTicketTextView;
        private TextView totalPrice;

        private AppCompatImageButton deleteImageView;
        private AppCompatImageButton editImageView;
        private AppCompatImageButton viewImageView;

        public ViewHolder (View view){
            super(view);
            numberTicketTextView = (TextView) view.findViewById(R.id.TextView_numberTicket);
            totalPrice = (TextView) view.findViewById(R.id.TextView_totalPrice);
            //deleteImageView = (AppCompatImageButton) view.findViewById(R.id.imageButton_delete);
            //editImageView = (AppCompatImageButton) view.findViewById(R.id.imageButton_edit);
            //viewImageView = (AppCompatImageButton) view.findViewById(R.id.imageButton_view);
        }

        public void updateViews(final Sale sale){
            numberTicketTextView.setText(sale.getCodeGuide());
            totalPrice.setText(Float.toString(sale.getPriceTotal()));
           /*deleteImageView.setOnClickListener(new View.OnClickListener() {
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
            });*/
        }
    }
}
