package com.example.telemedecin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class AddRdvActivity extends AppCompatActivity {

    private EditText etDoctorId, etDate, etTime, etPatientName;
    private Button btnAddRdv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rdv);

        etDoctorId = findViewById(R.id.etDoctorId);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        etPatientName = findViewById(R.id.etPatientName);
        btnAddRdv = findViewById(R.id.btnAddRdv);

        btnAddRdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctorId = etDoctorId.getText().toString().trim();
                String date = etDate.getText().toString().trim();
                String time = etTime.getText().toString().trim();
                String patientName = etPatientName.getText().toString().trim();

                if (!doctorId.isEmpty() && !date.isEmpty() && !time.isEmpty() && !patientName.isEmpty()) {
                    addRdvToDatabase(doctorId, date, time, patientName);
                } else {
                    Toast.makeText(AddRdvActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addRdvToDatabase(String doctorId, String date, String time, String patientName) {
        try {
            doctorId = URLEncoder.encode(doctorId, "UTF-8");
            date = URLEncoder.encode(date, "UTF-8");
            time = URLEncoder.encode(time, "UTF-8");
            patientName = URLEncoder.encode(patientName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = "http://10.0.2.2/TelemedecinProject/addrendezvous.php?doctor_id=" + doctorId + "&date=" + date + "&time=" + time + "&patient_name=" + patientName;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AddRdvActivity.this, "Rendez-vous ajouté avec succès !", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                },
                error -> Toast.makeText(AddRdvActivity.this, "Erreur : " + error.getMessage(), Toast.LENGTH_SHORT).show()
        );

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
