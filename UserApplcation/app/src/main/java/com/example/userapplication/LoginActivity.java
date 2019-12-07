package com.example.userapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Intent intentJoin;

    private Button btnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        intentJoin = new Intent(LoginActivity.this, MainActivity.class);

        btnJoin = findViewById(R.id.btnJoin);
        btnJoin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v == btnJoin){

            startActivity(intentJoin);

        }

    }
}
