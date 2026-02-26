package com.example.appgameinfantil;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class NumerosActivity extends AppCompatActivity {

    private TextView txtNumeroGrande;
    private int numeroCorreto;
    private Random random = new Random();
    private MediaPlayer mediaPlayer;
    private Button btnNum1, btnNum2, btnNum3, btnNum4;
    private int pontuacao = 0;
    private int rodadaAtual = 0;
    private final int TOTAL_RODADAS = 5;
    private TextView txtPontuacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros);

        txtNumeroGrande = findViewById(R.id.txtNumeroGrande);
        txtPontuacao = findViewById(R.id.txtPontuacao);

        btnNum1 = findViewById(R.id.btnNum1);
        btnNum2 = findViewById(R.id.btnNum2);
        btnNum3 = findViewById(R.id.btnNum3);
        btnNum4 = findViewById(R.id.btnNum4);

        btnNum1.setOnClickListener(v -> verificarResposta(1));
        btnNum2.setOnClickListener(v -> verificarResposta(2));
        btnNum3.setOnClickListener(v -> verificarResposta(3));
        btnNum4.setOnClickListener(v -> verificarResposta(4));

        sortearNumero();

        Button btnVoltar = findViewById(R.id.btnVoltar);

        btnVoltar.setOnClickListener(v -> {

            v.animate()
                    .scaleX(0.8f)
                    .scaleY(0.8f)
                    .setDuration(100)
                    .withEndAction(() -> {
                        v.animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(100)
                                .start();

                        finish();
                    })
                    .start();
        });
    }

    private void animarNumero() {

        txtNumeroGrande.setScaleX(0f);
        txtNumeroGrande.setScaleY(0f);

        txtNumeroGrande.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(250)
                .withEndAction(() ->
                        txtNumeroGrande.animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(150)
                                .start()
                )
                .start();
    }

    private void sortearNumero() {

        numeroCorreto = random.nextInt(4) + 1;

        txtNumeroGrande.setText(String.valueOf(numeroCorreto));

        animarNumero();

        tocarAudio(numeroCorreto);

        int[] cores = {
                getColor(R.color.corPrimaria),
                getColor(R.color.corSecundaria),
                getColor(R.color.corCachorro),
                getColor(R.color.corGato),
                getColor(R.color.corVaca)
        };

        int corAleatoria = cores[random.nextInt(cores.length)];
        txtNumeroGrande.setTextColor(corAleatoria);
    }

    private void tocarAudio(int numero) {

        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        int audio = 0;

        switch (numero) {
            case 1:
                audio = R.raw.um;
                break;
            case 2:
                audio = R.raw.dois;
                break;
            case 3:
                audio = R.raw.tres;
                break;
            case 4:
                audio = R.raw.quatro;
                break;
        }

        mediaPlayer = MediaPlayer.create(this, audio);
        mediaPlayer.start();
    }

    private void verificarResposta(int resposta) {

        rodadaAtual++;

        if (resposta == numeroCorreto) {

            pontuacao++;
            txtPontuacao.setText("Pontos: " + pontuacao);

            efeitoAcerto();

            Toast.makeText(this, "Acertou! 🎉", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Tente novamente!", Toast.LENGTH_SHORT).show();
        }

        if (rodadaAtual >= TOTAL_RODADAS) {
            mostrarResultadoFinal();
        } else {
            sortearNumero();
        }
    }

    private void efeitoAcerto() {

        txtNumeroGrande.animate()
                .rotationBy(360f)
                .setDuration(500)
                .start();
    }

    private void mostrarResultadoFinal() {

        Intent intent = new Intent(this, ResultadoActivity.class);
        intent.putExtra("pontos", pontuacao);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }
}