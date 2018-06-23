package com.edu.upc.businessbook.viewcontrollers.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;
import com.androidnetworking.AndroidNetworking;
import com.edu.upc.businessbook.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText email;
    TextInputEditText password;
    private static final String STRING_PREFERENCE = "Session";
    private static final String ACCOUNT_TOKEN = "userToken";
    private static final String COMPANY_ID = "CompanyId";
    private static final String EMPLOYEE_ID = "EmployeeId";
    private boolean existe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }


    public void onLogin(View view){
        email=(TextInputEditText) findViewById(R.id.text_input_email_in);
        password=(TextInputEditText) findViewById(R.id.text_input_password_in);

        if(login(email.getText().toString(), password.getText().toString()) == true){
            launchHomeActivity();
        }else{
            register(email.getText().toString(), password.getText().toString());
        }
    }



    private void launchHomeActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity (intent);
        finish();
    }

    public boolean login(final String email, final String password) {

        AndroidNetworking.post(NewApi.postUserLoginUrl())
                .addBodyParameter("users", email)
                .addBodyParameter("password", password)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{
                            if("200".equalsIgnoreCase(response.getString("Code")))
                            {

                                SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCE,MODE_PRIVATE);
                                preferences.edit().putString(ACCOUNT_TOKEN,"Bearer "+response.getJSONObject("Result").getString("accessToken")).apply();
                                preferences.edit().putString(COMPANY_ID,response.getJSONObject("Result").getString("CompanyId")).apply();
                                preferences.edit().putString(EMPLOYEE_ID,response.getJSONObject("Result").getString("EmployeeId")).apply();

                                existe = true;
                            }else{
                                existe = false;
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.d("test", String.format("onError: %s",
                                error.getErrorDetail()));
                    }
                });
        return existe;
    }

    public void register(final String email, final String password){
        AndroidNetworking.post(NewApi.postUserRegisterUrl())
                .addBodyParameter("employeeId", "12")
                .addBodyParameter("users", email)
                .addBodyParameter("password", password)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        AndroidNetworking.post(NewApi.postUserLoginUrl())
                                .addBodyParameter("users", email)
                                .addBodyParameter("password", password)
                                .setTag("test")
                                .setPriority(Priority.MEDIUM)
                                .build()
                                .getAsJSONObject(new JSONObjectRequestListener() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCE,MODE_PRIVATE);
                                            preferences.edit().putString(ACCOUNT_TOKEN,"Bearer "+response.getJSONObject("Result").getString("accessToken")).apply();
                                            preferences.edit().putString(COMPANY_ID,response.getJSONObject("Result").getString("CompanyId")).apply();
                                            preferences.edit().putString(EMPLOYEE_ID,response.getJSONObject("Result").getString("EmployeeId")).apply();

                                            launchHomeActivity();
                                        }catch (JSONException e){
                                            e.printStackTrace();
                                        }

                                    }
                                    @Override
                                    public void onError(ANError error) {
                                        Log.d("test", String.format("onError: %s",
                                                error.getErrorDetail()));
                                    }
                                });
                    }
                    @Override
                    public void onError(ANError error) {
                        Log.d("test", String.format("onError: %s",
                                error.getErrorDetail()));
                    }
                });
    }

}