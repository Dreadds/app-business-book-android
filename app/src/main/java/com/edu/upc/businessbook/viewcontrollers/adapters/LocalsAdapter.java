package com.edu.upc.businessbook.viewcontrollers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.Local;
import com.edu.upc.businessbook.viewcontrollers.activities.LocalActivity;

import java.util.List;

public class LocalsAdapter extends RecyclerView.Adapter<LocalsAdapter.ViewHolder> {
    private List<Local> locals;

    public LocalsAdapter(List<Local> locals) {
        this.locals = locals;
    }

    public LocalsAdapter() {
    }

    @NonNull
    @Override
    public LocalsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.card_locals, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocalsAdapter.ViewHolder holder, int position) {
        holder.updateView(locals.get(position));
    }

    @Override
    public int getItemCount() {
        return locals.size();
    }

    public List<Local> getLocals() {
        return locals;
    }

    public LocalsAdapter setLocals(List<Local> locals) {
        this.locals = locals;
        return this;
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
        private void updateView(final Local local){
            localImageView.setImageResource(R.drawable.store);
            titleTextView.setText(local.getName());
            nameTextView.setText(local.getDirection());
            exploreTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    context.startActivity(new Intent(context, LocalActivity.class)
                            .putExtras(local.toBundle()));
                }
            });
        }
    }
}
