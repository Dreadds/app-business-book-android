package com.edu.upc.businessbook.viewcontrollers.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.models.CompanyEntity;
import com.edu.upc.businessbook.models.CompanyPostEntity;
import com.edu.upc.businessbook.models.LocationSpinner;
import com.edu.upc.businessbook.viewcontrollers.fragments.ProfileFragment;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class UpdateProfileActivity extends AppCompatActivity {

    private EditText nameUpdateEditext;
    private EditText emailUpdateEditex;
    private EditText phoneUpdateEditex;
    private EditText mobileUpdateEditex;
    private List<LocationSpinner> locations;
    private SharedPreferences result;
    private static final String COMPANY_ID = "CompanyId";
    private static final String STRING_PREFERENCE = "Session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        Context context = this;
        result = getSharedPreferences(STRING_PREFERENCE,context.MODE_PRIVATE);

        nameUpdateEditext = (EditText) findViewById(R.id.etUpdate_company_name);
        emailUpdateEditex = (EditText) findViewById(R.id.etUpdate_email);
        phoneUpdateEditex = (EditText) findViewById(R.id.etUpdate_phone);
        mobileUpdateEditex = (EditText) findViewById(R.id.etUpdate_mobile);



        String identification = result.getString("CompanyId","noClienteId");;
        getProfile(Integer.parseInt(identification));

        Button btnsave = (Button) findViewById(R.id.buttonUpdate_save);
        btnsave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context c = v.getContext();
                putProfile(c);
            }
        });

        Button btnCancel = (Button) findViewById(R.id.buttonUpdate_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void putProfile(final Context c){

        String token = result.getString("userToken","Token Expirado");
        String companyId = result.getString(COMPANY_ID, "No Company");
        String employeeId =  result.getString("EmployeeId","Token Expirado");


        CompanyPostEntity companyPostEntity= new CompanyPostEntity();

        companyPostEntity.setCompanyId(Integer.parseInt(companyId));
        companyPostEntity.setName(nameUpdateEditext.getText().toString());
        String email = emailUpdateEditex.getText().toString();
        companyPostEntity.setEmail(emailUpdateEditex.getText().toString());
        companyPostEntity.setPhone(phoneUpdateEditex.getText().toString());
        companyPostEntity.setMobile(mobileUpdateEditex.getText().toString());
        companyPostEntity.setEmployeeId(Integer.parseInt(employeeId));
        companyPostEntity.setLocationId(1);

        AndroidNetworking.put(NewApi.putDataProfileUrl())
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

                                Intent intent = new Intent( getApplicationContext(), MainActivity.class);
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


    public void getProfile(int id) {
        String token = result.getString("userToken","Token Expirado");
        AndroidNetworking
                .get(NewApi.getDataProfileUrl(id))
                .addHeaders("Authorization", token)
                .setPriority(Priority.LOW)
                .setTag("businessbook")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if ("200".equalsIgnoreCase(response.getString("Code"))) {

                                CompanyEntity companyEntity = new CompanyEntity(response.getJSONObject("Result").getInt("companyId"),
                                        response.getJSONObject("Result").getString("name"), response.getJSONObject("Result").getString("address"), response.getJSONObject("Result").getString("email"),
                                        response.getJSONObject("Result").getString("phone"), response.getJSONObject("Result").getString("mobile"));

                                nameUpdateEditext.setText(companyEntity.getName());
                                emailUpdateEditex.setText(companyEntity.getEmail());
                                phoneUpdateEditex.setText(companyEntity.getPhone());
                                mobileUpdateEditex.setText(companyEntity.getMobile());

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("CatchUp", anError.getErrorDetail());

                    }
                });
    }

}
