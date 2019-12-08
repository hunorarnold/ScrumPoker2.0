package com.example.adminapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private Intent intentReg;
    private Button btnReg;
    EditText txtEmail, txtPassword, txtConfirmPassword;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        intentReg = new Intent(RegisterActivity.this, LoginActivity.class);

        btnReg = findViewById(R.id.btn_REG);
        txtEmail = findViewById(R.id.addEmail);
        txtPassword = findViewById(R.id.addPassword);
        txtConfirmPassword = findViewById(R.id.confPassword);

        firebaseAuth = FirebaseAuth.getInstance();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String confirmPassword = txtConfirmPassword.getText().toString();

                Log.i(email,password);

                if(email.isEmpty()){

                    txtEmail.setError("Please enter email id");
                    txtEmail.requestFocus();
                }

                if(password.isEmpty()){

                    txtPassword.setError("Please enter password");
                    txtPassword.requestFocus();

                }

                if(password.isEmpty()){

                    txtConfirmPassword.setError("Please enter password again");
                    txtConfirmPassword.requestFocus();

                }

                if(password.length() < 6){

                    Toast.makeText(RegisterActivity.this, "Password too short",
                            Toast.LENGTH_SHORT).show();
                    return;

                }

                if(password.equals(confirmPassword)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        startActivity(intentReg);
                                        Toast.makeText(RegisterActivity.this,
                                                "Sign Up successful", Toast.LENGTH_SHORT).show();

                                    } else {

                                        Toast.makeText(RegisterActivity.this,
                                                "Authentication Failed", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });

                }


            }
        });

    }

}
