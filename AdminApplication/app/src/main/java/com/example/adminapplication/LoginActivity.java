package com.example.adminapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Intent intentLogin, intentRegister;

    private Button btnLogin, btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intentLogin = new Intent(LoginActivity.this, MainActivity.class);
        intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);

        btnLogin = findViewById(R.id.btn_Login);
        btnLogin.setOnClickListener(this);

        btnReg = findViewById(R.id.btn_Register);
        btnReg.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v == btnLogin){

            startActivity(intentLogin);

        }

        if(v == btnReg){

            startActivity(intentRegister);

        }

    }
}
