package com.example.appgameinfantil;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    Button btnCores, btnAnimais, btnNumeros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCores = findViewById(R.id.btnCores);
        btnAnimais = findViewById(R.id.btnAnimais);
        btnNumeros = findViewById(R.id.btnNumeros);

        btnCores.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CoresActivity.class);
            startActivity(intent);
        });

        btnAnimais.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AnimaisActivity.class);
            startActivity(intent);
        });

        btnNumeros.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NumerosActivity.class);
            startActivity(intent);
        });
    }
}