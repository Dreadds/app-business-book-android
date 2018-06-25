package com.edu.upc.businessbook.viewcontrollers.activities.purchases;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.Purchase;
import com.edu.upc.businessbook.viewcontrollers.adapters.PurchaseAdapter;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class PurchaseActivity extends Activity {

    private java.util.List<Purchase> purchases ;
    private RecyclerView purchasesRecyclerView;
    private RecyclerView.LayoutManager purchasesLayoutManager;
    private PurchaseAdapter purchasesAdapter;
    private FloatingActionButton floatingActionButton;
    private SharedPreferences sp;
    private SharedPreferences result;
    private static final String COMPANY_ID = "CompanyId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);

        purchasesRecyclerView = findViewById(R.id.recyclerView_Purchase);
        purchases = new ArrayList<>();
        purchasesAdapter = new PurchaseAdapter(purchases);
        purchasesLayoutManager = new GridLayoutManager(PurchaseActivity.this,1);
        purchasesRecyclerView.setAdapter(purchasesAdapter);
        purchasesRecyclerView.setLayoutManager(purchasesLayoutManager);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.flotingActionButton_addPurchase);

        Context context = this;
        result = getSharedPreferences("Session",context.MODE_PRIVATE);
        int companyId = Integer.parseInt(result.getString(COMPANY_ID,"company no found"));
        getListPurchases(companyId);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),PurchaseAddActivity.class);
                startActivityForResult(intent,0);
            }
        });
    }


    private void getListPurchases(int companyId) {
        //TOKEN FOR AUTHORIZATION
        String token = result.getString("userToken","Token Expirado");
        //URL
        String url = NewApi.getListPurchaseUrl(companyId);
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
                                purchases = Purchase.Builder
                                        .from(response.getJSONArray("Result"))
                                        .buildAll();
                                purchasesAdapter.setPurchases(purchases);
                                purchasesAdapter.notifyDataSetChanged();
                                Log.d("BusinessBook", String.format("Purchases Count: %d", purchases.size()));
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
        ((GridLayoutManager) purchasesLayoutManager)
                .setSpanCount(getSpanCount(configuration));
    }
}
