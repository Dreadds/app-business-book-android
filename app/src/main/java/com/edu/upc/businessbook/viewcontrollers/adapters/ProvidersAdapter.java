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
import com.edu.upc.businessbook.models.Provider;

import java.util.List;

public class ProvidersAdapter extends RecyclerView.Adapter<ProvidersAdapter.ViewHolder>{
    private List<Provider> providers;

    public ProvidersAdapter() {
    }

    public ProvidersAdapter(List<Provider> providers) {

        this.providers = providers;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public ProvidersAdapter setProviders(List<Provider> providers) {
        this.providers = providers;
        return this;
    }

    @NonNull
    @Override
    public ProvidersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_provider, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProvidersAdapter.ViewHolder holder, int position) {
        holder.updateView(providers.get(position));
    }

    @Override
    public int getItemCount() {
        return providers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ANImageView logoProvider;
        TextView nameTextView, phoneTextView, emailTextView;
        CardView layoutItemProvider;
        public ViewHolder(View view) {
            super(view);
            logoProvider = (ANImageView) view.findViewById(R.id.logoProviderANImageView);
            nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            phoneTextView = (TextView) view.findViewById(R.id.phoneTextView);
            emailTextView = (TextView) view.findViewById(R.id.emailTextView);
            layoutItemProvider = (CardView) view.findViewById(R.id.layout_itemProvider);
        }
        public void updateView(Provider provider){
            logoProvider.setDefaultImageResId(R.drawable.box);
            logoProvider.setErrorImageResId(R.drawable.box);
            nameTextView.setText(provider.getName());
            phoneTextView.setText(provider.getPhone());
            emailTextView.setText(provider.getEmail());
        }
    }
}
