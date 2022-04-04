package com.example.testing01;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {

    EditText EFullname, EEmail, EPassword, ConfirmPass;
    Button ERegisterBtn;
    TextView EloginBtn;
    FirebaseAuth FAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        EFullname = findViewById(R.id.FullName);
        EEmail = findViewById(R.id.EmailRegister);
        EPassword = findViewById(R.id.PasswordRegister);
        ConfirmPass = findViewById(R.id.Phone);
        ERegisterBtn = findViewById(R.id. RegisterBtn);
        EloginBtn = findViewById(R.id.loginBtn);

        FAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        EloginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), User_Login.class));
            }
        });




        ERegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = EEmail.getText().toString().trim();
                String password = EPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    EEmail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    EEmail.setError("Password is Required");
                    return;
                }
                if (password.length() < 6) {
                    EPassword.setError("Password must be greater than 6 Characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                FAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registration.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), OnBoardingActivity.class));

                        } else {
                            Toast.makeText(Registration.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
}