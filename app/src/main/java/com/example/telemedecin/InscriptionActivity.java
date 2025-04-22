package com.example.telemedecin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InscriptionActivity extends AppCompatActivity {

    EditText editName, editPhone, editPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        editPassword = findViewById(R.id.editPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            // Plus tard : Ajouter une vraie logique d’inscription (BDD locale ou Firebase)
            Toast.makeText(this, "Compte créé avec succès", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(InscriptionActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
