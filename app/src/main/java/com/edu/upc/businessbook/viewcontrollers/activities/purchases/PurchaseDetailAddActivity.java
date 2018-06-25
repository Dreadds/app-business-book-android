package com.edu.upc.businessbook.viewcontrollers.activities.purchases;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.Modelo;
import com.edu.upc.businessbook.ModeloPurchase;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.ProductSpinner;
import com.edu.upc.businessbook.models.PurchaseDetail;
import com.edu.upc.businessbook.models.PurchaseDetailEntity;
import com.edu.upc.businessbook.viewcontrollers.adapters.PurchaseDetailAdapter;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class PurchaseDetailAddActivity extends Activity {

    private java.util.List<PurchaseDetail> purchaseDetails;
    private java.util.List<PurchaseDetailEntity> listPurchaseDetail;
    private PurchaseDetailEntity purchaseDetailEntity;
    private PurchaseDetail purchaseDetail;
    private RecyclerView purchaseDetailsRecyclerView;
    private RecyclerView.LayoutManager purchaseDetailsLayoutManager;
    private PurchaseDetailAdapter purchaseDetailsAdapter;
    private FloatingActionButton floatingActionButton;
    private FloatingActionButton saveFloatingActionButton;
    private List<ProductSpinner> products;
    private SharedPreferences result;
    private SharedPreferences resultPurchaseId;
    private static final String COMPANY_ID = "CompanyId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_detail_add);

        purchaseDetailsRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_purchaseDetail);
        purchaseDetails = new ArrayList<>();
        products = new ArrayList<>();
        listPurchaseDetail = new ArrayList<>();

        Context context = this;
        result = getSharedPreferences("Session",context.MODE_PRIVATE);
        resultPurchaseId = getSharedPreferences("SavePurchaseId",context.MODE_PRIVATE);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        purchaseDetailsRecyclerView.setLayoutManager(llm);

        purchaseDetailsAdapter = new PurchaseDetailAdapter(purchaseDetails);
        purchaseDetailsRecyclerView.setAdapter(purchaseDetailsAdapter);


        floatingActionButton = (FloatingActionButton) findViewById(R.id.addPurchaseDetail_flotingActionButtonPurchase);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] qua = purchaseDetailsAdapter.getQuantitys();
                String[] pId = purchaseDetailsAdapter.getProducts();
                String[] pU = purchaseDetailsAdapter.getPunits();
                String[] pP = purchaseDetailsAdapter.getProductsPos();
                String[] sT = purchaseDetailsAdapter.getSptotals();

                for(int i = 0;i< qua.length;i++){
                    purchaseDetail = new PurchaseDetail();
                    if(qua[i] != null) {
                        int quanti = Integer.parseInt(qua[i]);
                        int produId = Integer.parseInt(pId[i]);
                        int punit = Integer.parseInt(pU[i]);
                        int pPos = Integer.parseInt(pP[i]);
                        int sTot = Integer.parseInt(sT[i]);
                        purchaseDetail.setQuantity(quanti);
                        purchaseDetail.setProductId(produId);
                        purchaseDetail.setUnitPrice(punit);
                        purchaseDetail.setProductPos(pPos);
                        purchaseDetail.setPriceSubTotal(sTot);
                        purchaseDetails.set(i,purchaseDetail);
                    }
                }
                int companyId = Integer.parseInt(result.getString(COMPANY_ID,"company no found"));
                getProducts(companyId);
            }
        });
        saveFloatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton_savaPurchaseDetail);
        saveFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //PASAR LA VENTA ID para
                Context c = v.getContext();
                postPurchaseDetail(c);
            }
        });
    }

    private void postPurchaseDetail(final Context con){
        String token = result.getString("userToken","Token Expirado");
        int compraId = resultPurchaseId.getInt("purchaseId",0);
        String url = NewApi.postPurchaseDetail(compraId);

        String[] qua = purchaseDetailsAdapter.getQuantitys();
        String[] pId = purchaseDetailsAdapter.getProducts();
        String[] pU = purchaseDetailsAdapter.getPunits();
        String[] pP = purchaseDetailsAdapter.getProductsPos();
        String[] sT = purchaseDetailsAdapter.getSptotals();

        for(int i = 0;i< qua.length;i++){
            purchaseDetail = new PurchaseDetail();
            if(qua[i] != null) {
                int quanti = Integer.parseInt(qua[i]);
                int produId = Integer.parseInt(pId[i]);
                int punit = Integer.parseInt(pU[i]);
                int pPos = Integer.parseInt(pP[i]);
                int sTot = Integer.parseInt(sT[i]);
                purchaseDetail.setQuantity(quanti);
                purchaseDetail.setProductId(produId);
                purchaseDetail.setUnitPrice(punit);
                purchaseDetail.setProductPos(pPos);
                purchaseDetail.setPriceSubTotal(sTot);
                purchaseDetails.set(i,purchaseDetail);
            }
        }

        for (PurchaseDetail sd: purchaseDetails) {
            purchaseDetailEntity = new PurchaseDetailEntity();
            purchaseDetailEntity.productId = sd.ProductId;
            purchaseDetailEntity.priceSubTotal = sd.PriceSubTotal;
            purchaseDetailEntity.unitPrice = sd.UnitPrice;
            purchaseDetailEntity.quantity = sd.Quantity;
            listPurchaseDetail.add(purchaseDetailEntity);
        }


        String json = new Gson().toJson(listPurchaseDetail);
        String json2 = "{" + "\"listPurchaseDetail\":" + json + "}";


        ModeloPurchase m = new ModeloPurchase(listPurchaseDetail);

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


                                Intent intent = new Intent(con,PurchaseActivity.class);
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

                            purchaseDetails.add(new PurchaseDetail());

                            purchaseDetailsAdapter = new PurchaseDetailAdapter(purchaseDetails, adapter);
                            purchaseDetailsLayoutManager = new GridLayoutManager(PurchaseDetailAddActivity.this,1);
                            purchaseDetailsRecyclerView.setAdapter(purchaseDetailsAdapter);
                            purchaseDetailsRecyclerView.setLayoutManager(purchaseDetailsLayoutManager);

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
        ((GridLayoutManager) purchaseDetailsLayoutManager).setSpanCount(getSpanCount(configuration));
    }
}
