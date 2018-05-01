package com.edu.upc.businessbook.viewcontrollers.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.upc.businessbook.R;

public class LocalsAdapter extends RecyclerView.Adapter<LocalsAdapter.ViewHolder> {

    @NonNull
    @Override
    public LocalsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LocalsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView localImageView;
        private TextView titleTextView;
        private TextView nameTextView;
        private TextView exploreTextView;
        private CardView localLayout;
        public ViewHolder(View view) {
            super(view);
            localImageView = (ImageView) view.findViewById(R.id.localImageView);
            titleTextView = (TextView) view.findViewById(R.id.titleTextView);
            nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            exploreTextView = (TextView) view.findViewById(R.id.exploreTextView);
            localLayout = (CardView) view.findViewById(R.id.layout_local);
        }

    }
}
