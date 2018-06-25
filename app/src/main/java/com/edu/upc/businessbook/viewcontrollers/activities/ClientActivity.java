package com.edu.upc.businessbook.viewcontrollers.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.ClientItem;
import com.edu.upc.businessbook.network.BusinessBookApi;
import com.edu.upc.businessbook.viewcontrollers.adapters.ClientsAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClientActivity extends AppCompatActivity {
    private RecyclerView clientsRecyclerView;
    private ClientsAdapter clientsAdapter;
    private RecyclerView.LayoutManager clientLayoutManager;
    private List<ClientItem> clientItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        clientsRecyclerView = findViewById(R.id.recycler_clients);
        clientItems = new ArrayList<>();
        clientsAdapter = new ClientsAdapter(clientItems);
        clientLayoutManager = new GridLayoutManager(ClientActivity.this, 1);
        clientsRecyclerView.setAdapter(clientsAdapter);
        clientsRecyclerView.setLayoutManager(clientLayoutManager);
        getListClients(1);
    }

    private void getListClients(int companyId){
        AndroidNetworking.get(BusinessBookApi.getClientsUrl(companyId))
                .addHeaders("Authorization", getString(R.string.business_api_key))
                .setPriority(Priority.LOW)
                .setTag(R.string.TAG_app)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null){
                            try {
                                clientItems =ClientItem.Builder.from(response.getJSONArray("Result")).buildAll();
                                clientsAdapter.setClientItems(clientItems);
                                clientsAdapter.notifyDataSetChanged();
                                Log.d("businessbook", String.format("Providers Count: %d", clientItems.size()));
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
