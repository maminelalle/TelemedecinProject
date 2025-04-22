package com.example.telemedecin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RendezVousActivity extends AppCompatActivity {

    EditText editDate, editHeure, editMotif;
    Button btnValider;
    DatabaseHelper db;
    int userId = 1; // Temporaire pour le test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendez_vous);

        editDate = findViewById(R.id.editDate);
        editHeure = findViewById(R.id.editHeure);
        editMotif = findViewById(R.id.editMotif);
        btnValider = findViewById(R.id.btnValider);

        db = new DatabaseHelper(this);

        // Bouton de validation
        btnValider.setOnClickListener(v -> {
            String date = editDate.getText().toString();
            String heure = editHeure.getText().toString();
            String motif = editMotif.getText().toString();

            // Vérifie que tous les champs sont remplis
            if (date.isEmpty() || heure.isEmpty() || motif.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            // Insère le rendez-vous dans la base de données
            boolean success = db.insertRendezVous(date, heure, motif, userId);
            if (success) {
                Toast.makeText(this, "Rendez-vous enregistré !", Toast.LENGTH_SHORT).show();
                finish();  // Ferme l'activité après l'enregistrement
            } else {
                Toast.makeText(this, "Erreur, réessaye.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class RendezVousListActivity extends AppCompatActivity {
        private RecyclerView recyclerView;
        private RendezVousAdapter adapter;
        private List<RendezVous> rendezVousList;
        private DatabaseHelper db;
        private int userId = 1; // Utilisateur actuel
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rendez_vous_list);
    
            recyclerView = findViewById(R.id.recyclerView);
            db = new DatabaseHelper(this);
            rendezVousList = db.getAllRendezVous(userId);
    
            adapter = new RendezVousAdapter(rendezVousList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

}
