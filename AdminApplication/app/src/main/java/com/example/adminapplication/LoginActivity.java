package com.example.adminapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Intent intentLogin, intentRegister;
    private Button btnLogin, btnRegister;
    EditText emailId, passwordId;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();

        emailId = findViewById(R.id.Email);
        passwordId = findViewById(R.id.Password);
        btnLogin = findViewById(R.id.btn_Login);
        btnRegister = findViewById(R.id.btn_Register);

        intentLogin = new Intent(LoginActivity.this, MainActivity.class);
        intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);

        btnRegister.setOnClickListener(this);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser != null){

                    Toast.makeText(LoginActivity.this, "You are logged in",
                            Toast.LENGTH_SHORT).show();

                    startActivity(intentLogin);
                }

                else{

                    Toast.makeText(LoginActivity.this, "Please Login", Toast.LENGTH_SHORT).show();

                }

            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailId.getText().toString();
                String password = passwordId.getText().toString();

                Log.i(email,password);

                if(email.isEmpty()){

                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }

                if(password.isEmpty()){

                    passwordId.setError("Please enter password");
                    passwordId.requestFocus();

                }

                if(!(email.isEmpty() && password.isEmpty())){

                    mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(!task.isSuccessful()){

                                Toast.makeText(LoginActivity.this, "Login error, please try again", Toast.LENGTH_SHORT).show();

                            }

                            else
                            {

                                startActivity(intentLogin);

                            }

                        }
                    });

                }

            }
        });



    }

    @Override
    public void onClick(View v) {

        if(v == btnRegister){

            startActivity(intentRegister);

        }

    }

    @Override
    protected void onStart() {

        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
