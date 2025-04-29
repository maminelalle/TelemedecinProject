package com.example.telemedecin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    private Button btnAddDoctor, btnAddRdv, btnListDoctors, btnLogout;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialisation des boutons et du SearchView
        btnAddDoctor = findViewById(R.id.btnAddDoctor);
        btnAddRdv = findViewById(R.id.btnAddRdv);
        btnListDoctors = findViewById(R.id.btnDoctorsList);
        btnLogout = findViewById(R.id.btnLogout);
        searchView = findViewById(R.id.searchView);

        // Écouteur pour le bouton Ajouter un Médecin
        btnAddDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AddDoctorActivity.class);
                startActivity(intent);
            }
        });

        // Écouteur pour le bouton Ajouter un Rendez-vous
        btnAddRdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AddRdvActivity.class);
                startActivity(intent);
            }
        });

        // Écouteur pour le bouton Liste des Médecins
        btnListDoctors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, MedecinsActivity.class);
                startActivity(intent);
            }
        });

        // Écouteur pour le bouton Déconnexion
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Comportement du SearchView (juste un toast pour l'instant)
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Affichage d'un toast pour la recherche (à compléter)
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
