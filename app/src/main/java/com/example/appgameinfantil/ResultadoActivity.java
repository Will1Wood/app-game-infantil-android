package com.example.appgameinfantil;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.Button;
import nl.dionsegijn.konfetti.xml.KonfettiView;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.models.Size;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import android.graphics.Color;

public class ResultadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        TextView txtResultado = findViewById(R.id.txtResultado);

        KonfettiView konfettiView = findViewById(R.id.konfettiView);

        int pontos = getIntent().getIntExtra("pontos", 0);

        if (pontos == 5) {

            txtResultado.setText("PERFEITO! 🎉 5 de 5!");

            konfettiView.start(
                    new PartyFactory(
                            new Emitter(3, TimeUnit.SECONDS).perSecond(180)
                    )
                            .spread(360)
                            .sizes(new Size(12, 5f, 2f))
                            .colors(Arrays.asList(Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.BLUE))
                            .setSpeedBetween(10f, 18f)
                            .build()
            );
        }

        Button btnVoltarMenu = findViewById(R.id.btnVoltarMenu);

        btnVoltarMenu.setOnClickListener(v -> {

            Intent intent = new Intent(ResultadoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });


    }
}