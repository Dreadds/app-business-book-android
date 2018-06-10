package com.edu.upc.businessbook.viewcontrollers.activities.sales;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.edu.upc.businessbook.R;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.models.ClientSpinner;
import com.edu.upc.businessbook.models.LocalSpinner;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SaleAddActivity extends Activity {

    private EditText numberGuideEditText;
    private Spinner clienteSpinner;
    private Spinner employeeSpinner;
    private Spinner localSpinner;
    private FloatingActionButton nextFlotingActionButton;
    private List<ClientSpinner> clients;
    private List<LocalSpinner> locals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_add);

        numberGuideEditText = (EditText) findViewById(R.id.editText_NumberGuide);
        employeeSpinner = (Spinner) findViewById(R.id.spinner_nameEmployee);

        clients = new ArrayList<>();
        locals = new ArrayList<>();

        getClients(1);
        getLocals(1);

        //Sale Detail
        nextFlotingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton_next);
        nextFlotingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(v.getContext(),SaleDetailAddActivity.class);
                //startActivityForResult(intent,0);
            }
        });
    }


    private void getClients(int companyId){
        //URL
        String url = NewApi.getListClient(companyId);
        //TOKEN FOR AUTHORIZATION
        String token = "Bearer vLxEZavwttCG1T1AYcrqM5ia-Y6HeaS_TvbGxQ6ncqVarofCagsbCODP3CcWa0sTNRN2xk_tQkI2smkAJltYl9aMAqM2j8QpFjn8-PlANlBwREyB0QF4QRjOYqn7LPodRqDFh-fjTYN-OO4mHn7f4GxMZo9GPlOWGX8PkGmn3IafjtgmhrTZS99sbxpK0gtdrR2RPAnuC6qlEB35lNuMaL85CHOdOiqP4G2OaGaoizv-mnkecsfU4ePdgf5mwhLdNqVMD4541Jam_Msi5L2ifQ";

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

                            for (int i = 0; i <jsonClient.length();i++) {
                                clients.add(new ClientSpinner(jsonClient.getJSONObject(i).getInt("clientId"),
                                        jsonClient.getJSONObject(i).getString("fullName")));
                            }

                            ArrayAdapter<ClientSpinner> adapter =
                                    new ArrayAdapter<ClientSpinner>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, clients);
                            adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                            clienteSpinner.setAdapter(adapter);

                        }
                        catch (JSONException jex){

                        }
                    }
                    @Override
                    public void onError(ANError error) {
                    }
                });
    }

    private void getLocals(int companyId){
        //URL
        String url = NewApi.getListLocal(companyId);
        //TOKEN FOR AUTHORIZATION
        String token = "Bearer vLxEZavwttCG1T1AYcrqM5ia-Y6HeaS_TvbGxQ6ncqVarofCagsbCODP3CcWa0sTNRN2xk_tQkI2smkAJltYl9aMAqM2j8QpFjn8-PlANlBwREyB0QF4QRjOYqn7LPodRqDFh-fjTYN-OO4mHn7f4GxMZo9GPlOWGX8PkGmn3IafjtgmhrTZS99sbxpK0gtdrR2RPAnuC6qlEB35lNuMaL85CHOdOiqP4G2OaGaoizv-mnkecsfU4ePdgf5mwhLdNqVMD4541Jam_Msi5L2ifQ";

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

                            for (int i = 0; i <jsonLocal.length();i++) {
                                locals.add(new LocalSpinner(jsonLocal.getJSONObject(i).getInt("localId"),
                                        jsonLocal.getJSONObject(i).getString("name")));
                            }

                            ArrayAdapter<LocalSpinner> adapter =
                                    new ArrayAdapter<LocalSpinner>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, locals);
                            adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                            localSpinner.setAdapter(adapter);

                        }
                        catch (JSONException jex){

                        }
                    }
                    @Override
                    public void onError(ANError error) {
                    }
                });
    }

    private void postSale(String nameGuide,float priceTotal, int clientId, int employeeId, int localId){

        /*String url = NewApi.postSale();
        //pasar un modelo
        AddSaleModel addSaleModel = new AddSaleModel();
        addSaleModel.codeGuide = nameGuide;
        addSaleModel.localId = localId;
        addSaleModel.EmployeeId = employeeId;
        addSaleModel.clientId = clientId;
        addSaleModel.priceTotal = priceTotal;

        AndroidNetworking.post(url)
                .addBodyParameter(addSaleModel)
                .setTag("businessbook")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });*/
    }
}
