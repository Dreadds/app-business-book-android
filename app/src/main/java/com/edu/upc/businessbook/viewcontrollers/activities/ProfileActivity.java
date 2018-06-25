package com.edu.upc.businessbook.viewcontrollers.activities;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.CompanyPostEntity;
import com.edu.upc.businessbook.models.LocationSpinner;
import com.edu.upc.businessbook.viewcontrollers.fragments.ProfileFragment;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    private EditText nameEditext;
    private Spinner addressSpinner;
    private EditText emailEditex;
    private EditText phoneEditex;
    private EditText mobileEditex;
    private List<LocationSpinner> locations;
    private SharedPreferences result;
    private static final String COMPANY_ID = "CompanyId";
    private static final String STRING_PREFERENCE = "Session";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Context context = this;
        result = getSharedPreferences("Session",context.MODE_PRIVATE);

        nameEditext = (EditText) findViewById(R.id.et_company_name);
        addressSpinner = (Spinner) findViewById(R.id.et_address);
        emailEditex = (EditText) findViewById(R.id.et_email);
        phoneEditex = (EditText) findViewById(R.id.et_phone);
        mobileEditex = (EditText) findViewById(R.id.et_mobile);

        Button btnsave = (Button) findViewById(R.id.button_save);
        btnsave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context c = v.getContext();
                postProfile(c);
            }
        });
    }


    public void postProfile(final Context c){

        String token = result.getString("userToken","Token Expirado");

        CompanyPostEntity companyPostEntity= new CompanyPostEntity();

        companyPostEntity.setName(nameEditext.getText().toString());
        companyPostEntity.setEmail(emailEditex.getText().toString());
        companyPostEntity.setPhone(phoneEditex.getText().toString());
        companyPostEntity.setMobile(mobileEditex.getText().toString());
        String employeeId =  result.getString("EmployeeId","Token Expirado");
        companyPostEntity.setEmployeeId(Integer.parseInt(employeeId));
        companyPostEntity.setLocationId(1);

        AndroidNetworking.post(NewApi.postDataProfileUrl())
                .addHeaders("Authorization",token)
                .addBodyParameter(companyPostEntity)
                .setTag("businessbook")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            int prueba = 0;
                            if ("200".equalsIgnoreCase(response.getString("Code"))) {
                                SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCE,MODE_PRIVATE);
                                preferences.edit().putString(COMPANY_ID,response.getJSONObject("Result").getString("CompanyId")).apply();

                                Intent intent = new Intent(ProfileActivity.this, ProfileFragment.class);
                                startActivity(intent);
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        int errore = 1;
                    }
                });
    }

    public void getLocations(){
    String url=NewApi.getLocationUrl();
    String token = result.getString("userToken","Token Expirado");
            AndroidNetworking.get(url)
                .addHeaders("Authorization",token)
                .setTag("businessbook")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            JSONArray jsonLocation= response.getJSONArray("Result");
                            addressSpinner=(Spinner)findViewById(R.id.et_address);
                            for(int i=0;i<jsonLocation.length();i++){
                                locations.add(new LocationSpinner(jsonLocation.getJSONObject(i).getInt("locationId"),
                                        jsonLocation.getJSONObject(i).getString("name")));
                            }

                            ArrayAdapter<LocationSpinner>adapter=new ArrayAdapter<LocationSpinner>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,locations);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            addressSpinner.setAdapter(adapter);
                        }catch (JSONException jex){

                        }
                        // do anything with response
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });

    }

}
