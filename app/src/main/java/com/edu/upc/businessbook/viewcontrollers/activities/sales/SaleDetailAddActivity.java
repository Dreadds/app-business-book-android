package com.edu.upc.businessbook.viewcontrollers.activities.sales;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.LocalSpinner;
import com.edu.upc.businessbook.models.ProductSpinner;
import com.edu.upc.businessbook.models.Sale;
import com.edu.upc.businessbook.models.SaleDetail;
import com.edu.upc.businessbook.viewcontrollers.adapters.SaleAdapter;
import com.edu.upc.businessbook.viewcontrollers.adapters.SaleDetailAdapter;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class SaleDetailAddActivity extends AppCompatActivity {

    private java.util.List<SaleDetail> saleDetails;
    private RecyclerView saleDetailsRecyclerView;
    private RecyclerView.LayoutManager saleDetailsLayoutManager;
    private SaleDetailAdapter saleDetailsAdapter;
    private FloatingActionButton floatingActionButton;
    private List<ProductSpinner> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_detail_add);

        saleDetailsRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_saleDetail);
        saleDetails = new ArrayList<>();
        products = new ArrayList<>();

        getProducts(1);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.addSaleDetail_flotingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saleDetails.add(new SaleDetail());
            }
        });
    }

    private void getProducts(int companyId) {
        //URL
        String url = NewApi.getListProduct(companyId);
        //TOKEN FOR AUTHORIZATION
        String token = "Bearer rveB9K5roI4dlOyfqv-JDMlncKYODBBmuP2S7YXIkBK93AdZH-TDwfXUjatjwz5ANyYq6qS5IQQmeH7ld7PrD4T-YBO5dOg9KzKlW_B24hkHUial-FnI81od5gJqrRuWhK7pOaRNe8L-LVRpT-YbARxUBv0IW4Dl0Fmx2iHn2wodc99Nm0qjy-uIoIeexh7ozObzTcpM2D-RZg8p_Vly2HIn08G0cS__A1g7Pj_aM93FPFn3WCy9gwPXEU9G88jxq4SD2tTcnasRwHqEhx6AEA";

        AndroidNetworking
                .get(url)
                .addHeaders("Authorization", token)
                .setTag("businessbook")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonProduct = response.getJSONArray("Result");
                            for (int i = 0; i < jsonProduct.length(); i++) {
                                products.add(new ProductSpinner(jsonProduct.getJSONObject(i).getInt("productId"),
                                        jsonProduct.getJSONObject(i).getString("name")));
                            }
                            ArrayAdapter<ProductSpinner> adapter = new ArrayAdapter<ProductSpinner>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, products);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            saleDetailsAdapter = new SaleDetailAdapter(saleDetails, adapter);
                            saleDetailsLayoutManager = new GridLayoutManager(SaleDetailAddActivity.this,1);
                            saleDetailsRecyclerView.setAdapter(saleDetailsAdapter);
                            saleDetailsRecyclerView.setLayoutManager(saleDetailsLayoutManager);
                        }
                        catch (JSONException jex)
                        {
                            jex.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                    }
                });
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
        ((GridLayoutManager) saleDetailsLayoutManager).setSpanCount(getSpanCount(configuration));
    }


}
