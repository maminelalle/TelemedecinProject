package com.example.telemedecin;

import android.os.Bundle;
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

public class RendezVousActivity extends AppCompatActivity {

    private RecyclerView recyclerViewRdv;
    private RendezVousAdapter rdvAdapter;
    private List<RendezVous> rdvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendez_vous);

        recyclerViewRdv = findViewById(R.id.recyclerViewRdv);
        recyclerViewRdv.setLayoutManager(new LinearLayoutManager(this));

        rdvList = new ArrayList<>();
        rdvAdapter = new RendezVousAdapter(rdvList);
        recyclerViewRdv.setAdapter(rdvAdapter);

        // Charger les rendez-vous depuis la base de données distante
        getRdvFromDatabase();
    }

    private void getRdvFromDatabase() {
        String url = "http://10.0.2.2/TelemedecinProject/getrendezvous.php"; // L'URL de ton API PHP

        // Utilisation de Volley pour effectuer la requête
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject rdvJson = response.getJSONObject(i);
                                int id = rdvJson.getInt("id");
                                String date = rdvJson.getString("date");
                                String heure = rdvJson.getString("time");
                                String motif = rdvJson.getString("motif");

                                // Créer un objet RendezVous et l'ajouter à la liste
                                RendezVous rendezVous = new RendezVous(id, date, heure, motif);
                                rdvList.add(rendezVous);
                            }

                            // Notifier l'adaptateur que la liste a été mise à jour
                            rdvAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RendezVousActivity.this, "Erreur lors du parsing des données.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                error -> Toast.makeText(RendezVousActivity.this, "Erreur : " + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        // Ajouter la requête à la file d'attente de Volley
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }
}
