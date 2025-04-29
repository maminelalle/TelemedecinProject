package com.example.telemedecin;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class AddDoctorActivity extends AppCompatActivity {

    private EditText etDoctorName, etDoctorSpeciality, etDoctornumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);

        etDoctorName = findViewById(R.id.etDoctorName);
        etDoctorSpeciality = findViewById(R.id.etDoctorSpeciality);
        etDoctornumero = findViewById(R.id.etDoctornumero);

        findViewById(R.id.btnAddDoctor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctorName = etDoctorName.getText().toString().trim();
                String speciality = etDoctorSpeciality.getText().toString().trim();
                String numero = etDoctornumero.getText().toString().trim();

                if (!doctorName.isEmpty() && !speciality.isEmpty() && !numero.isEmpty()) {
                    addDoctorToDatabase(doctorName, speciality, numero);
                } else {
                    Toast.makeText(AddDoctorActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addDoctorToDatabase(String doctorName, String speciality, String numero) {
        String url = "http://10.0.2.2/TelemedecinProject/adddoctor.php"
                + "?name=" + doctorName
                + "&speciality=" + speciality
                + "&numero=" + numero;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AddDoctorActivity.this, "Médecin ajouté avec succès!", Toast.LENGTH_SHORT).show();
                        finish(); // Retourner au Dashboard
                    }
                },
                error -> Toast.makeText(AddDoctorActivity.this, "Erreur : " + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
