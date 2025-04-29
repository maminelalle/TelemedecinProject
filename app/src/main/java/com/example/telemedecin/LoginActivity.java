package com.example.telemedecin;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    private EditText editnumero, editPassword;
    private Button btnLogin;
    private TextView textCreateAccount;
    private static final String LOGIN_URL = "http://192.168.100.32/TelemedecinProject/getuser.php"; // Remplacez par l'IP locale


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editnumero = findViewById(R.id.editnumero);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
        textCreateAccount = findViewById(R.id.textCreateAccount);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero = editnumero.getText().toString();
                String password = editPassword.getText().toString();

                if (!numero.isEmpty() && !password.isEmpty()) {
                    new UserLoginTask().execute(numero, password);
                } else {
                    Toast.makeText(LoginActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, InscriptionActivity.class);
                startActivity(intent);
            }
        });
    }

    private class UserLoginTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String numero = params[0];
            String password = params[1];

            try {
                URL url = new URL(LOGIN_URL + "?numero=" + numero + "&password=" + password);
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
                    return "Login successful";
                } else {
                    return "Invalid credentials";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result.equals("Login successful")) {
                Toast.makeText(LoginActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
