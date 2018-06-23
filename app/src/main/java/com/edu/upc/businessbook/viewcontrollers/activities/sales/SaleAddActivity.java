package com.edu.upc.businessbook.viewcontrollers.activities.sales;

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
import com.edu.upc.businessbook.R;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.models.AddSaleModel;
import com.edu.upc.businessbook.models.ClientSpinner;
import com.edu.upc.businessbook.models.LocalSpinner;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SaleAddActivity extends Activity {

    private EditText numberGuideEditText;
    private Spinner clienteSpinner;
    private Spinner localSpinner;
    private FloatingActionButton nextFlotingActionButton;
    private List<ClientSpinner> clients;
    private List<LocalSpinner> locals;
    private SharedPreferences result;
    private SharedPreferences spVentaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_add);

        numberGuideEditText = (EditText) findViewById(R.id.editText_NumberGuide);

        clients = new ArrayList<>();
        locals = new ArrayList<>();

        Context context = this;
        result = getSharedPreferences("Session",context.MODE_PRIVATE);

        getClients(1);
        getLocals(1);

        //Sale Detail
        nextFlotingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton_next);
        nextFlotingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context c = v.getContext();
                postSale(c);
            }
        });
    }


    private void getClients(int companyId) {
        //URL
        String url = NewApi.getListClient(companyId);
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
                            JSONArray jsonClient = response.getJSONArray("Result");
                            clienteSpinner = (Spinner) findViewById(R.id.spinner_nameClient);

                            for (int i = 0; i < jsonClient.length(); i++) {
                                clients.add(new ClientSpinner(jsonClient.getJSONObject(i).getInt("clientId"),
                                        jsonClient.getJSONObject(i).getString("fullName")));
                            }

                            ArrayAdapter<ClientSpinner> adapter =
                                    new ArrayAdapter<ClientSpinner>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, clients);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            clienteSpinner.setAdapter(adapter);

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
                            JSONArray jsonLocal = response.getJSONArray("Result");
                            localSpinner = (Spinner) findViewById(R.id.spinner_nameLocal);

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

    private void postSale(final Context con) {
       // try {
        String url = NewApi.postSale();
        String token = result.getString("token","Token Expirado");
        AddSaleModel addSaleModel = new AddSaleModel();

            //Client ID
            Adapter adapterClient = clienteSpinner.getAdapter();
            int posClient = clienteSpinner.getSelectedItemPosition();
            ClientSpinner cs = (ClientSpinner) adapterClient.getItem(posClient);
            addSaleModel.clientId = cs.getClientId();

            //Local ID
            Adapter adapterLocal = localSpinner.getAdapter();
            int posLocal = localSpinner.getSelectedItemPosition();
            LocalSpinner ls = (LocalSpinner) adapterLocal.getItem(posLocal);
            addSaleModel.localId = ls.getLocalId();


            addSaleModel.codeGuide = numberGuideEditText.getText().toString();
            addSaleModel.companyId = 1;

            //Gson gson = new Gson();
            //String js = gson.toJson(addSaleModel);
            //JSONObject jo = new JSONObject(js);

            AndroidNetworking.post(url)
                    .addHeaders("Authorization", token)
                    .addBodyParameter(addSaleModel)
                    .setTag(this)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if ("200".equalsIgnoreCase(response.getString("Code"))) {

                                    spVentaId = getSharedPreferences("SaveSaleId",con.MODE_PRIVATE);
                                    SharedPreferences.Editor editorSale = spVentaId.edit();
                                    editorSale.putInt("saleId",1);
                                    editorSale.apply();

                                    Intent intent = new Intent(con,SaleDetailAddActivity.class);
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
