package com.example.telemedecin;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InscriptionActivity extends AppCompatActivity {

    private EditText editName, editnumero, editPassword;
    private Button btninscription;
    private static final String inscription_URL = "http://10.0.2.2/TelemedecinProject/adduser.php"; // Change si besoin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription); // Assure-toi que ton fichier XML s'appelle bien activity_inscription.xml

        editName = findViewById(R.id.editName);
        editnumero = findViewById(R.id.editnumero);
        editPassword = findViewById(R.id.editPassword);
        btninscription = findViewById(R.id.btnRegister);

        btninscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                String numero = editnumero.getText().toString();
                String password = editPassword.getText().toString();

                if (!name.isEmpty() && !numero.isEmpty() && !password.isEmpty()) {
                    new UserinscriptionTask().execute(name, numero, password);
                } else {
                    Toast.makeText(InscriptionActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class UserinscriptionTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String numero = params[1];
            String password = params[2];

            try {
                URL url = new URL(inscription_URL + "?name=" + name + "&numero=" + numero + "&password=" + password);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                String success = jsonResponse.getString("success");

                if (success.equals("1")) {
                    return "Inscription réussie";
                } else {
                    return "Échec de l'inscription";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Erreur : " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Toast.makeText(InscriptionActivity.this, result, Toast.LENGTH_SHORT).show();

            if (result.equals("Inscription réussie")) {
                finish(); // Retourner à l'écran précédent (ex : LoginActivity)
            }
        }
    }
}
