package com.edu.upc.businessbook.viewcontrollers.activities.sales;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.edu.upc.businessbook.R;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.models.AddSaleModel;
<<<<<<< HEAD
import com.edu.upc.businessbook.models.ClientModel;
=======
import com.edu.upc.businessbook.models.Client;
>>>>>>> dbf662a94f771a39caa9253941763a8fbd10d386
import com.edu.upc.businessbook.models.Sale;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;


public class SaleAddActivity extends Activity {

    private EditText numberGuideEditText;
    private Spinner clienteSpinner;
    private Spinner employeeSpinner;
    private Spinner localSpinner;
    private FloatingActionButton nextFlotingActionButton;
<<<<<<< HEAD
    private List<ClientModel> clients;
=======
    private List<Client> listClient;
>>>>>>> dbf662a94f771a39caa9253941763a8fbd10d386

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_add);

        numberGuideEditText = (EditText) findViewById(R.id.editText_NumberGuide);
        employeeSpinner = (Spinner) findViewById(R.id.spinner_nameEmployee);
        localSpinner = (Spinner) findViewById(R.id.spinner_nameLocal);
        listClient = new ArrayList<>();


        clients = new ArrayList<>();
        getClients(1);

        //PASS A LIST TO SPINNER
        String[] arraySpinner = new String[] {
                "1", "2", "3", "4", "5"
        };


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


<<<<<<< HEAD
    private void getClients(int companyId){
        //URL
        String url = NewApi.getListClient(companyId);
        //TOKEN FOR AUTHORIZATION
        String token = "Bearer vLxEZavwttCG1T1AYcrqM5ia-Y6HeaS_TvbGxQ6ncqVarofCagsbCODP3CcWa0sTNRN2xk_tQkI2smkAJltYl9aMAqM2j8QpFjn8-PlANlBwREyB0QF4QRjOYqn7LPodRqDFh-fjTYN-OO4mHn7f4GxMZo9GPlOWGX8PkGmn3IafjtgmhrTZS99sbxpK0gtdrR2RPAnuC6qlEB35lNuMaL85CHOdOiqP4G2OaGaoizv-mnkecsfU4ePdgf5mwhLdNqVMD4541Jam_Msi5L2ifQ";

        AndroidNetworking
                .get(url)
                .addHeaders("Authorization", token)
=======

    private void getListClient(int clientId){

        String token = "Bearer F_g2fJIUcNoplfEqxkRnKyuoJ8-wgi1DrpfaUrKy0lsqvN5p1MUcNUI4hOixxPixztWyTa4vCupxj96Xt5xyPQ_fU7v43XMuNinqvmjoAeLQOux1CqrHMd-sSpIlQL6XT5oUzUVSPD8e0JfhoDiemnPo9wox-bUIuIXI4u4qjtW04bXBW9A7AaBIeeNksDJr3GlGKNK5FQ-5fjd0nHNeQUjjCiRc7GleEfPqJ1zSSY70sVDV5zPDKqA_5XBxDfZXCzhhB15cZ0wYSi7nsXUFOb_lLSrBVhSEVThUHu7QOsM";
        //String url = "http://chemita96-001-site1.dtempurl.com/businessbookapi/v1/sales";
        String url = NewApi.getListClientUrl(clientId);

        AndroidNetworking
                .get(url)//TODO * URL DE LA LISTA
                .addHeaders("Authorization", token) //TODO * INVESTIGAR COMO PASAR EL TOKEN
>>>>>>> dbf662a94f771a39caa9253941763a8fbd10d386
                .setTag("businessbook")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
<<<<<<< HEAD
                            JSONArray jsonClient = response.getJSONArray("Result");
                            clienteSpinner = (Spinner) findViewById(R.id.spinner_nameClient);

                            for (int i = 0; i <jsonClient.length();i++) {
                                clients.add(new ClientModel(jsonClient.getJSONObject(i).getInt("clientId"),
                                        jsonClient.getJSONObject(i).getString("name")));
                            }

                            ArrayAdapter<ClientModel> adapter =
                                    new ArrayAdapter<ClientModel>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, clients);
                            adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                            clienteSpinner.setAdapter(adapter);

                        }
                        catch (JSONException jex){

                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
=======
                            if (response != null) {
                                JSONArray jsonClient = response.getJSONArray("Result");
                                for(int i = 0; i<jsonClient.length(); i++){
                                    listClient.add(new Client(jsonClient.getJSONObject(i).getInt("ClientId"),jsonClient.getJSONObject(i).getString("FullName")));
                                }
                            }
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("BusinessBook", anError.getErrorDetail());
                        String valor = anError.toString();
                        String[] split1 = valor.split("Value");
                        String[] split2 = split1[1].split("of");
                        int status = anError.getErrorCode();
>>>>>>> dbf662a94f771a39caa9253941763a8fbd10d386
                    }
                });
    }

<<<<<<< HEAD
=======

>>>>>>> dbf662a94f771a39caa9253941763a8fbd10d386
    private void postSale(String nameGuide,float priceTotal, int clientId, int employeeId, int localId){

        String url = NewApi.postSale();
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
                });
    }
}
