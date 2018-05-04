package com.edu.upc.businessbook.viewcontrollers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.Item;
import com.edu.upc.businessbook.models.ItemsRepository;
import com.edu.upc.businessbook.models.Producto;
import com.edu.upc.businessbook.network.BusinessBookApi;
import com.edu.upc.businessbook.viewcontrollers.adapters.ItemsAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ItemActivity extends AppCompatActivity {
    Item item;
    ItemsRepository itemsRepository;
    TextView nameItemTextView;
    List<Producto> products;

    private ItemsAdapter itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if(intent == null) return;
        item = Item.Builder.from(intent.getExtras()).build();
        toolbar.setTitle(item.getName());
        updateView();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void updateView(){
        AndroidNetworking.get(BusinessBookApi.getProductsUrl())
                .setPriority(Priority.LOW)
                .setTag("BusinessBook")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("error".equalsIgnoreCase(response.getString("status"))){
                                Log.d("BusinessBook", response.getString("message"));
                                return;
                            }
                            products = Producto.Builder.from(response.getJSONArray("products"))
                                    .buildAll();
                            Log.d("BusinessBook", String.format("Products Count %d",
                                    products.size()));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


    }

}
