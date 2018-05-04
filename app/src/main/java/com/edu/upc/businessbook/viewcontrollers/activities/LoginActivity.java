package com.edu.upc.businessbook.viewcontrollers.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.edu.upc.businessbook.R;

public class LoginActivity extends AppCompatActivity {

    String user="ana@gmail.com";
    String pass = "123";

    TextInputEditText email;
    TextInputEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void onLogin(View view){
        email=(TextInputEditText) findViewById(R.id.text_input_email_in);
        password=(TextInputEditText) findViewById(R.id.text_input_password_in);

        if(email.getText().toString().equals(user) && password.getText().toString().equals(pass)){
            launchHomeActivity();
        }else {
            String message = "Usuario o contraseña no on correctos";
            Snackbar.make(view,message,Snackbar.LENGTH_LONG).show();
        }
    }

    private void launchHomeActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity (intent);
        finish();
    }

}
