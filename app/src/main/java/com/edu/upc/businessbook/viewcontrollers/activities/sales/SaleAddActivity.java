package com.edu.upc.businessbook.viewcontrollers.activities.sales;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.edu.upc.businessbook.R;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.models.AddSaleModel;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;

import org.json.JSONObject;


public class SaleAddActivity extends Activity {

    private EditText numberGuideEditText;
    private Spinner clienteSpinner;
    private Spinner employeeSpinner;
    private Spinner localSpinner;
    private FloatingActionButton nextFlotingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_add);

        numberGuideEditText = (EditText) findViewById(R.id.editText_NumberGuide);
        clienteSpinner = (Spinner) findViewById(R.id.spinner_nameClient);
        employeeSpinner = (Spinner) findViewById(R.id.spinner_nameEmployee);
        localSpinner = (Spinner) findViewById(R.id.spinner_nameLocal);
        nextFlotingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton_next);
        nextFlotingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(v.getContext(),SaleDetailAddActivity.class);
                //startActivityForResult(intent,0);
            }
        });
    }
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
