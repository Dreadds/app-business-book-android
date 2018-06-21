package com.edu.upc.businessbook.viewcontrollers.activities.sales;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.viewcontrollers.adapters.SaleAdapter;
import com.edu.upc.businessbook.models.Sale;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import java.util.*;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class SaleActivity extends  Activity {

    private java.util.List<Sale> sales ;
    private RecyclerView salesRecyclerView;
    private RecyclerView.LayoutManager salesLayoutManager;
    private SaleAdapter salesAdapter;
    private FloatingActionButton floatingActionButton;
    private SharedPreferences sp;


    public SaleActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);

        salesRecyclerView = findViewById(R.id.recyclerView_sale);
        sales = new ArrayList<>();
        salesAdapter = new SaleAdapter(sales);
        salesLayoutManager = new GridLayoutManager(SaleActivity.this,1);
        salesRecyclerView.setAdapter(salesAdapter);
        salesRecyclerView.setLayoutManager(salesLayoutManager);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.flotingActionButton_add);

        Context context = this;
        sp = getSharedPreferences("SaveSp",context.MODE_PRIVATE);
        //Guardar dato
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token","Bearer 12xR3teAuFBH6DxfKKtqbtEoyb_LQccBnbKfqpD553_ewgKq6zo17kRweaBVmUZer8u3cwzZG5Z-v_QRta21aS6ZCw6i4WdPDuo-ay0FIIvrC13lIki2dUP9y2JwR4TAbMcoNmrLRhkYj7aqlotolAKmwFvQSWeiWcJz40mPp6jpucKfzcWck29mUP0C6Er0NhsL1BPO7kZivfjqvyh4dDj3mF-uloTbCK4qc3oCAv42q8bNgRqh35SJfGWkkhSrL8E046IYesYISZZMz5CS_lg_TcCk7T7E750zJmgWbdo");
        editor.apply();

        getListSales(1);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),SaleAddActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }

    private void getListSales(int companyId) {
        //TOKEN FOR AUTHORIZATION
        String token = sp.getString("token","Token Expirado");
        //URL
        String url = NewApi.getListSaleUrl(companyId);
        AndroidNetworking
                .get(url)//TODO * URL DE LA LISTA
                .addHeaders("Authorization", token) //TODO * INVESTIGAR COMO PASAR EL TOKEN
                .setTag("businessbook")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response != null) {
                                sales = Sale.Builder
                                        .from(response.getJSONArray("Result"))
                                        .buildAll();
                                salesAdapter.setSales(sales);
                                salesAdapter.notifyDataSetChanged();
                                Log.d("BusinessBook", String.format("Sales Count: %d", sales.size()));
                            }
                        } catch (JSONException ex) {
                            ex.printStackTrace();
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
