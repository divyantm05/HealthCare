package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FindDoctorActivity extends AppCompatActivity {

    private CardView physicians_card,dietician_card,surgeon_card,cardiologists_card,dentist_card,back_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_doctor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        physicians_card=findViewById(R.id.physicians_card);
        dietician_card=findViewById(R.id.dietician_card);
        surgeon_card=findViewById(R.id.surgeon_card);
        cardiologists_card=findViewById(R.id.cardiologists_card);
        dentist_card=findViewById(R.id.dentist_card);

        back_card=findViewById(R.id.back_card);


        physicians_card.setOnClickListener(listener);
        dietician_card.setOnClickListener(listener);
        surgeon_card.setOnClickListener(listener);
        cardiologists_card.setOnClickListener(listener);
        dentist_card.setOnClickListener(listener);

        back_card.setOnClickListener(listener);

    }

    View.OnClickListener listener = v -> {
        Intent i =new Intent(FindDoctorActivity.this, HomeActivity.class);
        startActivity(i);
    };
}