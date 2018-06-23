package com.edu.upc.businessbook.viewcontrollers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.Provider;
import com.edu.upc.businessbook.network.BusinessBookApi;
import com.edu.upc.businessbook.viewcontrollers.adapters.ProvidersAdapter;
import com.edu.upc.businessbook.viewcontrollers.dialogs.DialogPersonalized;
import com.edu.upc.businessbook.viewcontrollers.dialogs.DialogProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProviderActivity extends AppCompatActivity {
    public Provider provider;
    private RecyclerView providersRecyclerView;
    private ProvidersAdapter providersAdapter;
    private RecyclerView.LayoutManager providersLayoutManager;
    private List<Provider> providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DialogProvider();
                dialogFragment.show(getSupportFragmentManager(), "Dialog");
            }
        });

        providersRecyclerView = findViewById(R.id.recycler_providers);
        providers = new ArrayList<>();
        providersAdapter = new ProvidersAdapter(providers);
        providersLayoutManager = new GridLayoutManager(ProviderActivity.this, 1);
        providersRecyclerView.setAdapter(providersAdapter);
        providersRecyclerView.setLayoutManager(providersLayoutManager);
        getListProviders(1);
        //Intent intent = getIntent();
        //if(intent == null) return;
        //provider = Provider.Builder.from(intent.getExtras()).build();
        toolbar.setTitle("Providers");


    }

    private void getListProviders(int companyId){
        AndroidNetworking.get(BusinessBookApi.getProvidersUrl(companyId))
                .addHeaders("Authorization", getString(R.string.business_api_key))
                .setPriority(Priority.LOW)
                .setTag(R.string.TAG_app)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null){
                            try {
                                providers = Provider.Builder.from(response.getJSONArray("Result")).buildAll();
                                providersAdapter.setProviders(providers);
                                providersAdapter.notifyDataSetChanged();
                                Log.d("businessbook", String.format("Providers Count: %d", providers.size()));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

}
