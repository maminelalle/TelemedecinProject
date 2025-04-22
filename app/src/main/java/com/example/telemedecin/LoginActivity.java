package com.example.telemedecin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText editPhone, editPassword;
    Button btnLogin;
    TextView textCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);  // Ce fichier sera créé juste après

        editPhone = findViewById(R.id.editPhone);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.btnLogin);
        textCreateAccount = findViewById(R.id.textCreateAccount);

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
        });

        textCreateAccount.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, InscriptionActivity.class);
            startActivity(intent);
        });
    }
}
