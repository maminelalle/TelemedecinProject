package com.example.telemedecin;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MedecinsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMedecins;
    private DoctorAdapter doctorAdapter;
    private List<Doctor> doctorList;
    private SearchView searchViewDoctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medecins);

//        recyclerViewMedecins = findViewById(R.id.recyclerViewMedecins);
//        searchViewDoctors = findViewById(R.id.searchViewDoctors);

        // Définir le LayoutManager pour le RecyclerView
        recyclerViewMedecins.setLayoutManager(new LinearLayoutManager(this));

        // Initialiser la liste des médecins et l'adaptateur
        doctorList = new ArrayList<>();
        doctorAdapter = new DoctorAdapter(this, doctorList);
        recyclerViewMedecins.setAdapter(doctorAdapter);

        // Charger les médecins depuis la base de données distante
        getDoctorsFromDatabase();

        // Ajouter un écouteur de texte pour la recherche des médecins
        searchViewDoctors.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                doctorAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                doctorAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    // Méthode pour récupérer les médecins depuis la base de données distante
    private void getDoctorsFromDatabase() {
        String url = "http://10.0.2.2/TelemedecinProject/getdoctor.php"; // L'URL de ton API PHP

        // Utilisation de Volley pour effectuer la requête
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Parcourir la réponse JSON et ajouter les médecins à la liste
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject doctorJson = response.getJSONObject(i);

                                // Extraire les données du JSON
                                int id = doctorJson.optInt("id", -1); // Récupérer l'id du médecin (utiliser -1 par défaut si absent)
                                String name = doctorJson.optString("name", "Nom non disponible");
                                String speciality = doctorJson.optString("speciality", "Spécialité non disponible");
                                String numero = doctorJson.optString("numero", "Numéro non disponible");

                                // Créer un objet Doctor avec les données extraites
                                Doctor doctor = new Doctor(id, name, speciality, numero);
                                doctorList.add(doctor);
                            }

                            // Notifier l'adaptateur que la liste a été mise à jour
                            doctorAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MedecinsActivity.this, "Erreur lors du parsing des données.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                error -> Toast.makeText(MedecinsActivity.this, "Erreur : " + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        // Ajouter la requête à la file d'attente de Volley
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }
}
