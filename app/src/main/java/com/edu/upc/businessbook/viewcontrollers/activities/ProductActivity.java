package com.edu.upc.businessbook.viewcontrollers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.Item;
import com.edu.upc.businessbook.models.ItemsRepository;
import com.edu.upc.businessbook.viewcontrollers.adapters.ItemsAdapter;

public class ProductActivity extends AppCompatActivity {
    Item item;
    ItemsRepository itemsRepository;
    TextView nameItemTextView;
    private ItemsAdapter itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        if(intent == null) return;
        item = Item.Builder.from(intent.getExtras()).build();
        //itemsAdapter = new ItemsAdapter(ItemsRepository.getInstance().getItems());
        nameItemTextView = (TextView) findViewById(R.id.nameItemTextView);

        updateView(item);


        //item = Item.Bun

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void updateView(Item item){
        nameItemTextView.setText(item.getName());

    }

}
