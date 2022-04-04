package com.example.testing01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.testing01.ui.home.HomeFragment;

public class User_Plumbing extends AppCompatActivity {

    ImageView backBtn;
    Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_plumbing);
        backBtn = findViewById(R.id.backBtn);
        Button nextButton = findViewById(R.id.next_btn);


        ImageView backBtn = (ImageView) findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), User_Booking_Cleaning.class));
            }
        });
    }

}