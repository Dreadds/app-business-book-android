package com.edu.upc.businessbook.viewcontrollers.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
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
import com.edu.upc.businessbook.models.Product;
import com.edu.upc.businessbook.network.BusinessBookApi;
import com.edu.upc.businessbook.viewcontrollers.adapters.ProductAdapter;
import com.edu.upc.businessbook.viewcontrollers.dialogs.DialogProduct;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    private RecyclerView productsRecyclerView;
    private ProductAdapter productAdapter;
    private RecyclerView.LayoutManager productLayoutManager;
    private List<Product> products;
    private SharedPreferences result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DialogProduct();
                dialogFragment.show(getSupportFragmentManager(), "Dialog");
            }
        });
        result = this.getSharedPreferences("Session", Context.MODE_PRIVATE);
        productsRecyclerView = findViewById(R.id.recycler_products);
        products = new ArrayList<>();
        productAdapter = new ProductAdapter(products);
        productLayoutManager = new GridLayoutManager(ProductActivity.this, 1);
        productsRecyclerView.setAdapter(productAdapter);
        productsRecyclerView.setLayoutManager(productLayoutManager);
        getListProducts(1);
        toolbar.setTitle("Products");

    }

    private void getListProducts(int companyId){
        String token = result.getString("userToken","Token Expirado");
        AndroidNetworking.get(BusinessBookApi.getProductsUrl(companyId))
                .addHeaders("Authorization", token)
                .setPriority(Priority.LOW)
                .setTag(R.string.TAG_app)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null){
                            try {
                                products = Product.Builder.from(response.getJSONArray("Result")).buildAll();
                                productAdapter.setProducts(products);
                                productAdapter.notifyDataSetChanged();
                                Log.d("businessbook", String.format("Providers Count: %d", products.size()));
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
