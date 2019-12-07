package com.example.adminapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private Intent intentReg;

    private Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        intentReg = new Intent(RegisterActivity.this, LoginActivity.class);

        btnReg = findViewById(R.id.btn_Register);
        btnReg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v == btnReg){

            startActivity(intentReg);

        }

    }
}
