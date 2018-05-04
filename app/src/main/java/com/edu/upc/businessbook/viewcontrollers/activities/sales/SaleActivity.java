package com.edu.upc.businessbook.viewcontrollers.activities.sales;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.viewcontrollers.adapters.SalesAdapter;
import com.edu.upc.businessbook.viewcontrollers.models.Sale;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import java.util.*;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class SaleActivity extends  Activity {

    private java.util.List<Sale> sales ;
    private RecyclerView salesRecyclerView;
    private RecyclerView.LayoutManager salesLayoutManager;
    private SalesAdapter salesAdapter;
    private FloatingActionButton floatingActionButton;
    public SaleActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sale);


        salesRecyclerView = findViewById(R.id.recyclerView_sale);
        sales = new ArrayList<>();
        salesAdapter = new SalesAdapter(sales);
        salesLayoutManager = new GridLayoutManager(SaleActivity.this,1);
        salesRecyclerView.setAdapter(salesAdapter);
        salesRecyclerView.setLayoutManager(salesLayoutManager);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.flotingActionButton_add);
        updateData();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),AddSaleActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }

    private void updateData(){

        AndroidNetworking
                .get(NewApi.getSaleUrl())
                .setTag("BusinessBook")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(org.json.JSONArray response) {
                        if(response != null) {
                            sales = Sale.Builder
                                    .from(response)
                                    .buildAll();
                            salesAdapter.setSales(sales);
                            salesAdapter.notifyDataSetChanged();
                            Log.d("BusinessBook",
                                    String.format("Sales Count: %d",
                                            sales.size()));
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d("BusinessBook", anError.getErrorDetail());
                        String valor = anError.toString();
                        String[] split1 = valor.split("Value");
                        String[] split2 = split1[1].split("of");
                        int status = anError.getErrorCode();

                    }
                });

    }


    public interface responseCallback<T> {
        void onResponse(T response);

        void onError(String message);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateLayout(newConfig);
    }

    private int getSpanCount(Configuration configuration) {
        return configuration.orientation ==
                ORIENTATION_PORTRAIT ? 2 : 3;
    }

    private void updateLayout(Configuration configuration) {
        ((GridLayoutManager) salesLayoutManager)
                .setSpanCount(getSpanCount(configuration));
    }
}
