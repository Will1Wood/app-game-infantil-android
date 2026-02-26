package com.example.appgameinfantil;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.TextView;

public class CoresActivity extends AppCompatActivity {

    private Button btnVoltar;

    private TextView txtFeedback;

    private ConstraintLayout layout;
    private Button btnVermelho, btnAzul, btnAmarelo, btnVerde;

    private void animarCor(int novaCor) {
        int corAtual = Color.WHITE;

        ValueAnimator animator = ValueAnimator.ofObject(
                new ArgbEvaluator(),
                corAtual,
                novaCor
        );

        animator.setDuration(500); // meio segundo

        animator.addUpdateListener(animation ->
                layout.setBackgroundColor((int) animation.getAnimatedValue())
        );

        animator.start();
    }

    private void tocarSom(int som) {
        MediaPlayer mp = MediaPlayer.create(this, som);
        mp.start();
        mp.setOnCompletionListener(MediaPlayer::release);
    }

    private void mostrarFeedback() {
        txtFeedback.setVisibility(View.VISIBLE);
        txtFeedback.setScaleX(0f);
        txtFeedback.setScaleY(0f);

        txtFeedback.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(300)
                .withEndAction(() -> txtFeedback.postDelayed(() ->
                        txtFeedback.setVisibility(View.GONE), 800))
                .start();
    }

    private void animarBotao(Button botao) {
        botao.animate()
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(100)
                .withEndAction(() ->
                        botao.animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(100)
                );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cores);

        btnVoltar = findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(v -> finish());

        txtFeedback = findViewById(R.id.txtFeedback);

        // Referência ao layout inteiro
        layout = findViewById(R.id.layoutCores);

        // Referência aos botões
        btnVermelho = findViewById(R.id.btnVermelho);
        btnAzul = findViewById(R.id.btnAzul);
        btnAmarelo = findViewById(R.id.btnAmarelo);
        btnVerde = findViewById(R.id.btnVerde);

        // Clique em Vermelho
        btnVermelho.setOnClickListener(v -> {
            animarBotao(btnVermelho);
            animarCor(Color.RED);
            tocarSom(R.raw.vermelho);
            mostrarFeedback();
        });

        // Clique em Azul
        btnAzul.setOnClickListener(v -> {
            animarBotao(btnAzul);
            animarCor(Color.BLUE);
            tocarSom(R.raw.azul);
            mostrarFeedback();
        });

        // Clique em Amarelo
        btnAmarelo.setOnClickListener(v -> {
            animarBotao(btnAmarelo);
            animarCor(Color.YELLOW);
            tocarSom(R.raw.amarelo);
            mostrarFeedback();
        });

        // Clique em Verde
        btnVerde.setOnClickListener(v -> {
            animarBotao(btnVerde);
            animarCor(Color.GREEN);
            tocarSom(R.raw.verde);
            mostrarFeedback();
        });
    }
}