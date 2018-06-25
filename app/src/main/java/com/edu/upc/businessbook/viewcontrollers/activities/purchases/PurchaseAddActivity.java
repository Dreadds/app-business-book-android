package com.edu.upc.businessbook.viewcontrollers.activities.purchases;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.AddPurchaseModel;
import com.edu.upc.businessbook.models.LocalSpinner;
import com.edu.upc.businessbook.models.ProveedorSpinner;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PurchaseAddActivity extends Activity {

    private EditText numberGuideEditText;
    private Spinner proveedorSpinner;
    private Spinner localSpinner;
    private FloatingActionButton nextFlotingActionButton;
    private List<ProveedorSpinner> proveedors;
    private List<LocalSpinner> locals;
    private SharedPreferences result;
    private SharedPreferences spCompraId;
    private static final String COMPANY_ID = "CompanyId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_add);

        numberGuideEditText = (EditText) findViewById(R.id.editText_NumberGuidePurchase);

        proveedors = new ArrayList<>();
        locals = new ArrayList<>();

        Context context = this;
        result = getSharedPreferences("Session",context.MODE_PRIVATE);
        int companyId = Integer.parseInt(result.getString(COMPANY_ID,"company no found"));
        getProveedors(companyId);
        getLocals(companyId);

        nextFlotingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton_nextPurchase);
        nextFlotingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                postPurchase(c);
            }
        });
    }

    private void getProveedors(int companyId) {
        //URL
        String url = NewApi.getListProveedor(companyId);
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
                            JSONArray jsonProveedor = response.getJSONArray("Result");
                            proveedorSpinner = (Spinner) findViewById(R.id.spinner_nameProveedorPurchase);

                            for (int i = 0; i < jsonProveedor.length(); i++) {
                                proveedors.add(new ProveedorSpinner(jsonProveedor.getJSONObject(i).getInt("providerId"),
                                        jsonProveedor.getJSONObject(i).getString("name")));
                            }

                            ArrayAdapter<ProveedorSpinner> adapter =
                                    new ArrayAdapter<ProveedorSpinner>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, proveedors);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            proveedorSpinner.setAdapter(adapter);

                        } catch (JSONException jex) {

                        }
                    }

                    @Override
                    public void onError(ANError error) {
                    }
                });
    }

    private void getLocals(int companyId) {
        //URL
        String url = NewApi.getListLocal(companyId);
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
                            JSONArray jsonLocal = response.getJSONArray("Result");
                            localSpinner = (Spinner) findViewById(R.id.spinner_nameLocalPurchase);

                            for (int i = 0; i < jsonLocal.length(); i++) {
                                locals.add(new LocalSpinner(jsonLocal.getJSONObject(i).getInt("localId"),
                                        jsonLocal.getJSONObject(i).getString("name")));
                            }

                            ArrayAdapter<LocalSpinner> adapter =
                                    new ArrayAdapter<LocalSpinner>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, locals);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            localSpinner.setAdapter(adapter);

                        } catch (JSONException jex) {

                        }
                    }

                    @Override
                    public void onError(ANError error) {
                    }
                });
    }


    private void postPurchase(final Context con) {
        // try {
        String url = NewApi.postPurchase();
        String token = result.getString("userToken","Token Expirado");
        AddPurchaseModel addPurchaseModel = new AddPurchaseModel();

        //Proveedor ID
        Adapter adapterProveedor = proveedorSpinner.getAdapter();
        int posProveedor = proveedorSpinner.getSelectedItemPosition();
        ProveedorSpinner cs = (ProveedorSpinner) adapterProveedor.getItem(posProveedor);
        addPurchaseModel.providerId = cs.getProveedorId();

        //Local ID
        Adapter adapterLocal = localSpinner.getAdapter();
        int posLocal = localSpinner.getSelectedItemPosition();
        LocalSpinner ls = (LocalSpinner) adapterLocal.getItem(posLocal);
        addPurchaseModel.localId = ls.getLocalId();


        addPurchaseModel.codeGuide = numberGuideEditText.getText().toString();
        addPurchaseModel.companyId = 1;


        AndroidNetworking.post(url)
                .addHeaders("Authorization", token)
                .addBodyParameter(addPurchaseModel)
                .setTag(this)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("200".equalsIgnoreCase(response.getString("Code"))) {

                                response.getString("Result");
                                spCompraId = getSharedPreferences("SavePurchaseId",con.MODE_PRIVATE);
                                SharedPreferences.Editor editorPurchase = spCompraId.edit();

                                editorPurchase.putInt("purchaseId",Integer.parseInt(response.getJSONObject("Result").getString("PurchaseId")));
                                editorPurchase.apply();

                                Intent intent = new Intent(con,PurchaseDetailAddActivity.class);
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
        //}catch (JSONException e) {
        //  e.printStackTrace();
        //}
    }
}
