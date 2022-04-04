package com.example.testing01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testing01.activities.Technician_Setting;
import com.example.testing01.activities.Technician_SignIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;



public class Login_as extends AppCompatActivity {

    Button loginAsTechnician, loginAsClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as);

        loginAsTechnician = findViewById(R.id.loginAsTechnician);
        loginAsClient = findViewById(R.id.loginAsClient);

        loginAsTechnician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Login_as.this, Technician_SignIn.class);
                startActivity(intent);
                finish();

            }
        });
        loginAsClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login_as.this,User_Login.class);
                startActivity(intent);
                finish();            }
        });
    }
}