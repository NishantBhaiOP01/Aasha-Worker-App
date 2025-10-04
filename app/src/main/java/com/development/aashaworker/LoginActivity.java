package com.development.aashaworker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;

public class LoginActivity extends AppCompatActivity {

    Button btnAshaLogin;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnAshaLogin = findViewById(R.id.btnAshaLogin);
        btnAshaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                finish();
            }
        });
        MaterialAutoCompleteTextView dropdownLanguage = findViewById(R.id.dropdownLanguage);

        String[] languages = {"English", "हिंदी", "मराठी", "বাংলা", "తెలుగు"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                languages
        );

        dropdownLanguage.setAdapter(adapter);

// Optional: open dropdown on click
        dropdownLanguage.setOnClickListener(v -> dropdownLanguage.showDropDown());


    }
}