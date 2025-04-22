package com.example.telemedecin;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;

public class MedecinsActivity extends AppCompatActivity {

    ListView listViewMedecins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecins);

        listViewMedecins = findViewById(R.id.listViewMedecins);

        // Exemple de médecins
        String[] medecins = {
            "Dr. Ahmed - Pédiatre",
            "Dr. Binta - Cardiologue",
            "Dr. Salim - Gynécologue",
            "Dr. Khadija - Généraliste",
            "Dr. Oumar - Dermatologue"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, medecins);
        listViewMedecins.setAdapter(adapter);

        Button btnPrendreRdv = findViewById(R.id.btnPrendreRdv);
btnPrendreRdv.setOnClickListener(v -> {
    Intent intent = new Intent(MedecinsActivity.this, RendezVousActivity.class);
    startActivity(intent);
});

    }
}
