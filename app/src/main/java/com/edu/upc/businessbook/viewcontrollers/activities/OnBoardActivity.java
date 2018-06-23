package com.edu.upc.businessbook.viewcontrollers.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.edu.upc.businessbook.R;
import com.edu.upc.businessbook.viewcontrollers.network.NewApi;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.androidnetworking.AndroidNetworking;


import org.json.JSONException;
import org.json.JSONObject;

public class OnBoardActivity extends AppCompatActivity {

    public static int APP_REQUEST_CODE=1;
    private static final String STRING_PREFERENCE = "Session";
    private static final String ACCOUNT_TOKEN = "userToken";
    private String number="991234567";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        AccessToken accessToken = AccountKit.getCurrentAccessToken();

        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCE,MODE_PRIVATE);
        String token = preferences.getString(ACCOUNT_TOKEN,null);

        if (accessToken != null || token != null) {
            launchHomeActivity();
        }

    }

    private void launchHomeActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        //enviar el numero
        intent.putExtra("number", number);
        startActivity (intent);
        finish();
    }

    public void onPhoneLogin(View view){
        onLogin(LoginType.PHONE);
    }

    public void onEmailLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity (intent);
        finish();

    }

    private void onLogin (final LoginType loginType){
        final Intent intent = new Intent(this, AccountKitActivity.class);

        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        loginType,
                        AccountKitActivity.ResponseType.TOKEN
                );

        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // confirm that this response matches your request
        if (requestCode == APP_REQUEST_CODE) {
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            if (loginResult.getError() != null) {
                // login error
                String toastMessage = loginResult.getError().getErrorType().getMessage();
                Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
            } else if (loginResult.getAccessToken() != null) {
                // oK
                // AccessToken access_token = loginResult.getAccessToken();
                register();
                launchHomeActivity();
            }
        }
    }

    public void register(){
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                PhoneNumber phoneNumber = account.getPhoneNumber();
                if (phoneNumber != null) {
                    // if the phone number is available, display it
                    number = phoneNumber.toString();
                }else{

                }
            }

            @Override
            public void onError(AccountKitError accountKitError) {

            }
        });
        AndroidNetworking.post(NewApi.postUserRegisterUrl())
                .addBodyParameter("users", number)
                .addBodyParameter("password", "123")
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                    @Override
                    public void onError(ANError error) {
                        Log.d("test", String.format("onError: %s",
                                error.getErrorDetail()));
                    }
                });
    }
}