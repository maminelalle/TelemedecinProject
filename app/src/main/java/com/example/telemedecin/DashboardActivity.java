package com.example.telemedecin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    Button btnCardios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Référencer le bouton "Les cardios" dans le layout
        btnCardios = findViewById(R.id.btnCardios);

        // Ajouter un clic qui ouvre MedecinsActivity
        btnCardios.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, MedecinsActivity.class);
            startActivity(intent);
        });
    }
}
