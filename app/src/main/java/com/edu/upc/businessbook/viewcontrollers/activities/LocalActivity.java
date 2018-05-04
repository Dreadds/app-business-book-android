package com.edu.upc.businessbook.viewcontrollers.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.Item;
import com.edu.upc.businessbook.models.ItemsRepository;
import com.edu.upc.businessbook.models.Local;
import com.edu.upc.businessbook.viewcontrollers.adapters.ItemsAdapter;

import java.util.ArrayList;
import java.util.List;

public class LocalActivity extends AppCompatActivity {
    Local local;
    private RecyclerView itemsRecyclerView;
    private ItemsAdapter itemsAdapter;
    //LinearLayoutManager layoutManager;
    private RecyclerView.LayoutManager itemsLayoutManager;
    private List<Item> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        Toolbar toolbarLocal = (Toolbar) findViewById(R.id.toolbarLocal);
        setSupportActionBar(toolbarLocal);
        itemsRecyclerView = findViewById(R.id.recycler_items);
        items = new ArrayList<>();
        itemsAdapter = new ItemsAdapter(ItemsRepository.getInstance().getItems());
        itemsLayoutManager = new GridLayoutManager(this, 2);
        itemsRecyclerView.setAdapter(itemsAdapter);
        itemsRecyclerView.setLayoutManager(itemsLayoutManager);
        Intent intent = getIntent();
        if(intent == null) return;
        local = Local.Builder.from(intent.getExtras()).build();
        toolbarLocal.setTitle(local.getName());

        //Intent intent = getIntent();
        //if(intent == null) return;


        updateView();


    }

    private void updateView() {

    }

}
