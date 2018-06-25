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
import com.edu.upc.businessbook.Modelo;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.LocalSpinner;
import com.edu.upc.businessbook.models.ProductSpinner;
import com.edu.upc.businessbook.models.Sale;
import com.edu.upc.businessbook.models.SaleDetail;
import com.edu.upc.businessbook.models.SaleDetailEntity;
import com.edu.upc.businessbook.viewcontrollers.adapters.SaleAdapter;
import com.edu.upc.businessbook.viewcontrollers.adapters.SaleDetailAdapter;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class SaleDetailAddActivity extends Activity {

    private java.util.List<SaleDetail> saleDetails;
    private java.util.List<SaleDetailEntity> listSaleDetail;
    private SaleDetailEntity saleDetailEntity;
    private SaleDetail saleDetail;
    private RecyclerView saleDetailsRecyclerView;
    private RecyclerView.LayoutManager saleDetailsLayoutManager;
    private SaleDetailAdapter saleDetailsAdapter;
    private FloatingActionButton floatingActionButton;
    private FloatingActionButton saveFloatingActionButton;
    private List<ProductSpinner> products;
    private SharedPreferences result;
    private SharedPreferences resultSaleId;
    private static final String COMPANY_ID = "CompanyId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_detail_add);

        saleDetailsRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_saleDetail);
        saleDetails = new ArrayList<>();
        products = new ArrayList<>();
        listSaleDetail = new ArrayList<>();

        Context context = this;
        result = getSharedPreferences("Session",context.MODE_PRIVATE);
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
                String[] pId = saleDetailsAdapter.getProducts();
                String[] pU = saleDetailsAdapter.getPunits();
                String[] pP = saleDetailsAdapter.getProductsPos();
                String[] sT = saleDetailsAdapter.getSptotals();

                for(int i = 0;i< qua.length;i++){
                    saleDetail = new SaleDetail();
                    if(qua[i] != null) {
                        int quanti = Integer.parseInt(qua[i]);
                        int produId = Integer.parseInt(pId[i]);
                        int punit = Integer.parseInt(pU[i]);
                        int pPos = Integer.parseInt(pP[i]);
                        int sTot = Integer.parseInt(sT[i]);
                        saleDetail.setQuantity(quanti);
                        saleDetail.setProductId(produId);
                        saleDetail.setUnitPrice(punit);
                        saleDetail.setProductPos(pPos);
                        saleDetail.setPriceSubTotal(sTot);
                        saleDetails.set(i,saleDetail);
                    }
                }
                int companyId = Integer.parseInt(result.getString(COMPANY_ID,"company no found"));
                getProducts(companyId);
            }
        });
        saveFloatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton_savaSaleDetail);
        saveFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PASAR LA VENTA ID para
                Context c = v.getContext();
                postSaleDetail(c);
            }
        });
    }

    private void postSaleDetail(final Context con){
        String token = result.getString("userToken","Token Expirado");
        int ventaId = resultSaleId.getInt("saleId",0);
        String url = NewApi.postSaleDetail(ventaId);

        String[] qua = saleDetailsAdapter.getQuantitys();
        String[] pId = saleDetailsAdapter.getProducts();
        String[] pU = saleDetailsAdapter.getPunits();
        String[] pP = saleDetailsAdapter.getProductsPos();
        String[] sT = saleDetailsAdapter.getSptotals();

        for(int i = 0;i< qua.length;i++){
            saleDetail = new SaleDetail();
            if(qua[i] != null) {
                int quanti = Integer.parseInt(qua[i]);
                int produId = Integer.parseInt(pId[i]);
                int punit = Integer.parseInt(pU[i]);
                int pPos = Integer.parseInt(pP[i]);
                int sTot = Integer.parseInt(sT[i]);
                saleDetail.setQuantity(quanti);
                saleDetail.setProductId(produId);
                saleDetail.setUnitPrice(punit);
                saleDetail.setProductPos(pPos);
                saleDetail.setPriceSubTotal(sTot);
                saleDetails.set(i,saleDetail);
            }
        }
        //List<SaleDetail> Lst = saleDetails;

        for (SaleDetail sd: saleDetails) {
            saleDetailEntity = new SaleDetailEntity();
            saleDetailEntity.productId = sd.ProductId;
            saleDetailEntity.priceSubTotal = sd.PriceSubTotal;
            saleDetailEntity.unitPrice = sd.UnitPrice;
            saleDetailEntity.quantity = sd.Quantity;
            listSaleDetail.add(saleDetailEntity);
        }


        String json = new Gson().toJson(listSaleDetail);
        String json2 = "{" + "\"listSaleDetail\":" + json + "}";


        Modelo m = new Modelo(listSaleDetail);

        Gson g = new Gson();


        AndroidNetworking.post(url)
                .addHeaders("Authorization", token)
                .addHeaders("Content-Type", "application/json")
                .addApplicationJsonBody(m)
                //.add
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
        String token = result.getString("userToken","Token Expirado");
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
                            products.add(new ProductSpinner());
                            for (int i = 0; i < jsonProduct.length(); i++) {
                                products.add(new ProductSpinner(jsonProduct.getJSONObject(i).getInt("productId"),
                                        jsonProduct.getJSONObject(i).getString("name"), jsonProduct.getJSONObject(i).getInt("unitPrice")));
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
