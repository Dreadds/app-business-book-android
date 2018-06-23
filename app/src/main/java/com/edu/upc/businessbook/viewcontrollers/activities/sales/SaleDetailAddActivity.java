package com.edu.upc.businessbook.viewcontrollers.activities.sales;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import java.util.Arrays;
import java.util.List;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class SaleDetailAddActivity extends Activity {

    private java.util.List<SaleDetail> saleDetails;
    private SaleDetail saleDetail;
    private RecyclerView saleDetailsRecyclerView;
    private RecyclerView.LayoutManager saleDetailsLayoutManager;
    private SaleDetailAdapter saleDetailsAdapter;
    private FloatingActionButton floatingActionButton;
    private FloatingActionButton saveFloatingActionButton;
    private List<ProductSpinner> products;
    private SharedPreferences result;
    private SharedPreferences resultSaleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_detail_add);

        saleDetailsRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_saleDetail);
        saleDetails = new ArrayList<>();
        products = new ArrayList<>();

        Context context = this;
        result = getSharedPreferences("SaveSp",context.MODE_PRIVATE);
        resultSaleId = getSharedPreferences("SaveSaleId",context.MODE_PRIVATE);
        //saleDetails.add(saleDetail);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        saleDetailsRecyclerView.setLayoutManager(llm);

        saleDetailsAdapter = new SaleDetailAdapter(saleDetails);
        saleDetailsRecyclerView.setAdapter(saleDetailsAdapter);


        floatingActionButton = (FloatingActionButton) findViewById(R.id.addSaleDetail_flotingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] qua = saleDetailsAdapter.getQuantitys();
                for(int i = 0;i< qua.length;i++){
                    saleDetail = new SaleDetail();
                    if(qua[i] != null) {
                        int quanti = Integer.parseInt(qua[i]);
                        saleDetail.setQuantity(quanti);
                        saleDetail.setProductId(1);
                        saleDetail.setUnitPrice(2);
                        saleDetail.setPriceSubTotal(20);
                        saleDetails.set(i,saleDetail);
                    }
                }
                getProducts(1);
            }
        });
        saveFloatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton_savaSaleDetail);
        saveFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PASAR LA VENTA ID para
                Context c = v.getContext();
                for (SaleDetail sd: saleDetails) {
                    /*sd.setQuantity();
                    sd.setProductId();
                    sd.setUnitPrice();
                    sd.setPriceSubTotal();*/
                }
                postSaleDetail(c);
            }
        });
    }

    private void postSaleDetail(final Context con){
        String token = result.getString("token","Token Expirado");
        int ventaId = resultSaleId.getInt("saleId",0);
        String url = NewApi.postSaleDetail(ventaId);
        List<SaleDetail> Lst = saleDetails;

        AndroidNetworking.post(url)
                .addHeaders("Authorization", token)
                .addBodyParameter(products)
                .setTag(this)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("200".equalsIgnoreCase(response.getString("Code"))) {

                                /*SharedPreferences.Editor editorSale = spVentaId.edit();
                                editorSale.putInt("saleId",1);
                                editorSale.apply();*/

                                Intent intent = new Intent(con,SaleActivity.class);
                                startActivityForResult(intent,0);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        int i = 0;
                    }
                });
    }

    private void getProducts(int companyId) {
        //URL
        String url = NewApi.getListProduct(companyId);
        //TOKEN FOR AUTHORIZATION
        String token = result.getString("token","Token Expirado");
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
                            products.clear();
                            for (int i = 0; i < jsonProduct.length(); i++) {
                                products.add(new ProductSpinner(jsonProduct.getJSONObject(i).getInt("productId"),
                                        jsonProduct.getJSONObject(i).getString("name")));
                            }
                            ArrayAdapter<ProductSpinner> adapter = new ArrayAdapter<ProductSpinner>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, products);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            saleDetails.add(new SaleDetail());
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
