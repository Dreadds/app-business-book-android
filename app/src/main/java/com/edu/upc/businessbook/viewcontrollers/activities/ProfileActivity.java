package com.edu.upc.businessbook.viewcontrollers.activities;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameEditext=(EditText)findViewById(R.id.et_company_name);
        addressSpinner=(Spinner)findViewById(R.id.et_address);
        emailEditex=(EditText)findViewById(R.id.et_email);
        phoneEditex=(EditText)findViewById(R.id.et_phone);
        mobileEditex=(EditText)findViewById(R.id.et_mobile);

        Button btnsave=(Button) findViewById(R.id.button_save);
        btnsave.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v) {
            Context c=v.getContext();
            postProfile(c);
        }
     });


    }
    public void postProfile(final Context c){
        String token = "Bearer bAIzKTyzdDVBoN2IKF2ly9g_fMYFY6gtZk_3UOEh5jO44_u-ng9-Hru7-muEFY09ftcHFd0nWwZQ1cByOMI9vrCYKtwmU3Ozpe94kt1Wd9i-Wegxd2Urcowo6n6nzU4F1B_sCwbwc8tSGLG0Vga3-EITHnUy0h12fZxb9PDf5gzbrMtYl_uM5blrago12-qqY9F1yVUSGRUIU_L2YHwrxvNNeCrDPZo1upi8jx2Gses2NN7jNqkHkevwQOapnsrWtEKAtnWibXICI6fSd9VSjw";

        CompanyPostEntity companyPostEntity= new CompanyPostEntity();
        companyPostEntity.setName(nameEditext.getText().toString());

        companyPostEntity.setEmail(emailEditex.getText().toString());
        companyPostEntity.setPhone(phoneEditex.getText().toString());
        companyPostEntity.setMobile(mobileEditex.getText().toString());

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
                      int prueba = 0;
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });
    }
    public void getLocations(){
    String url=NewApi.getLocationUrl();
    String token = "Bearer kVFso18dz-0sHn3llcSrdzqt11om7EQfCwhKL0DB2MluD0XjYJkzoRnoj9QeoyjChwg82mk5D6o16Mp9x0dXuBifXUcEGdZhDB1c0s0JrDsndGVW2he1pcI5bK_V1Pk5yKgAJ6GjOjn0l9OF9-5Ooy5iNeUOmRF9XI-0kNdAc0nsghAAF5A9Xc67QaiN6--mrmuL7yS2fJL2FOg-scRxD-0SM1PMbOyG6KqOcsj-SGJJW4KQ8oyXI4yMoNl0jYgN2Pf8k2H72QQonzDHoAAWeg";
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
