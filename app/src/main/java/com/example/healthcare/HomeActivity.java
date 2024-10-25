package com.example.healthcare;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.EventListener;

public class HomeActivity extends AppCompatActivity {

    private CardView findDoctor,labTest,buyMedicine,healthArticle,orderDetails,exit;
    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);

        String username =sharedPreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(),"Welcome "+username, Toast.LENGTH_SHORT).show();

        findDoctor = findViewById(R.id.cardFindDoctor);
        labTest = findViewById(R.id.cardLabTest);
        buyMedicine = findViewById(R.id.cardByMedicine);
        healthArticle = findViewById(R.id.cardHealthDoctor);
        orderDetails = findViewById(R.id.cardOrderDetails);
        exit=findViewById(R.id.cardExit);


        findDoctor.setOnClickListener(listener);
        labTest.setOnClickListener(listener);
        buyMedicine.setOnClickListener(listener);
        healthArticle.setOnClickListener(listener);
        orderDetails.setOnClickListener(listener);

        exit.setOnClickListener(v->{
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.clear();
            editor.apply();
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        });


    }

    View.OnClickListener listener = v -> {
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear();
        editor.apply();
        startActivity(new Intent(HomeActivity.this, FindDoctorActivity.class));
    };

}