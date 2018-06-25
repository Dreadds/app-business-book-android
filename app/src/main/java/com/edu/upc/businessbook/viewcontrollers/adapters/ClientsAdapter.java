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
import com.edu.upc.businessbook.models.ClientItem;

import java.util.List;

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.ViewHolder> {
    private List<ClientItem> clientItems;

    public ClientsAdapter() {
    }

    public ClientsAdapter(List<ClientItem> clientItems) {
        this.clientItems = clientItems;
    }

    @NonNull
    @Override
    public ClientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_client, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ClientsAdapter.ViewHolder holder, int position) {
        holder.updateView(clientItems.get(position));

    }

    @Override
    public int getItemCount() {
        return clientItems.size();
    }

    public List<ClientItem> getClientItems() {
        return clientItems;
    }

    public ClientsAdapter setClientItems(List<ClientItem> clientItems) {
        this.clientItems = clientItems;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ANImageView logoClient;
        TextView nameTextView, phoneTextView, emailTextView, dniTextView;
        CardView layoutItemclient;
        public ViewHolder(View view) {
            super(view);
            logoClient = (ANImageView) view.findViewById(R.id.logoClientANImageView);
            nameTextView = (TextView) view.findViewById(R.id.fullNameTextView);
            phoneTextView = (TextView) view.findViewById(R.id.phoneTextView);
            emailTextView = (TextView) view.findViewById(R.id.emailTextView);
            dniTextView = (TextView) view.findViewById(R.id.dniTextView);
            layoutItemclient = (CardView) view.findViewById(R.id.layout_itemClient);
        }
        public void updateView(ClientItem clientItem){
            logoClient.setDefaultImageResId(R.drawable.client);
            logoClient.setErrorImageResId(R.drawable.client);
            nameTextView.setText(clientItem.getName());
            phoneTextView.setText(clientItem.getPhone());
            emailTextView.setText(clientItem.getEmail());
            dniTextView.setText(clientItem.getDni());
        }
    }
}
