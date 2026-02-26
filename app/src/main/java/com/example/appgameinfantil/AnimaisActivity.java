package com.example.appgameinfantil;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import java.util.Random;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import android.view.View;

public class AnimaisActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayerVitoria;
    private Button btnCachorro, btnGato, btnVaca, btnLeao, btnVoltar;
    private Button btnDesafio;
    private int animalCorreto = -1;
    private boolean modoDesafio = false;
    private Random random = new Random();
    private int pontuacao = 0;
    private TextView txtPontuacao;
    private int rodadaAtual = 0;
    private final int TOTAL_RODADAS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animais);

        txtPontuacao = findViewById(R.id.txtPontuacao);
        btnDesafio = findViewById(R.id.btnDesafio);
        btnDesafio.setOnClickListener(v -> iniciarDesafio());
        btnCachorro = findViewById(R.id.btnCachorro);
        btnGato = findViewById(R.id.btnGato);
        btnVaca = findViewById(R.id.btnVaca);
        btnLeao = findViewById(R.id.btnLeao);
        btnVoltar = findViewById(R.id.btnVoltarAnimais);

        btnCachorro.setOnClickListener(v -> {
            animarBotao(btnCachorro);

            if (modoDesafio) {
                verificarResposta(0);
            } else {
                tocarSom(R.raw.cachorro);
            }
        });

        btnGato.setOnClickListener(v -> {
            animarBotao(btnGato);
            if (modoDesafio) {
                verificarResposta(1);
            } else {
                tocarSom(R.raw.gato);
            }
        });

        btnVaca.setOnClickListener(v -> {
            animarBotao(btnVaca);
            if (modoDesafio) {
                verificarResposta(2);
            } else {
                tocarSom(R.raw.vaca);
            }
        });

        btnLeao.setOnClickListener(v -> {
            animarBotao(btnLeao);
            if (modoDesafio) {
                verificarResposta(3);
            } else {
                tocarSom(R.raw.leao);
            }
        });

        btnVoltar.setOnClickListener(v -> finish());
    }

    private void iniciarDesafio() {

        if (rodadaAtual >= TOTAL_RODADAS) {
            finalizarJogo();
            return;
        }

        modoDesafio = true;
        rodadaAtual++;

        animalCorreto = random.nextInt(4);

        switch (animalCorreto) {
            case 0:
                tocarSom(R.raw.cachorro);
                break;
            case 1:
                tocarSom(R.raw.gato);
                break;
            case 2:
                tocarSom(R.raw.vaca);
                break;
            case 3:
                tocarSom(R.raw.leao);
                break;
        }
    }

    private void tocarSom(int som) {
        MediaPlayer mp = MediaPlayer.create(this, som);
        mp.start();
        mp.setOnCompletionListener(MediaPlayer::release);
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

    private void verificarResposta(int resposta) {

        if (resposta == animalCorreto) {
            pontuacao++;
            txtPontuacao.setText("⭐ Pontos: " + pontuacao);
            Toast.makeText(this, "🎉 Muito bem!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "😅 Tente de novo!", Toast.LENGTH_SHORT).show();
        }

        // próxima rodada automática
        iniciarDesafio();
    }

    private void tocarSomVitoria() {

        if (mediaPlayerVitoria != null) {
            mediaPlayerVitoria.release();
        }

        mediaPlayerVitoria = MediaPlayer.create(this, R.raw.vitoria);
        mediaPlayerVitoria.start();
    }

    private void finalizarJogo() {

        modoDesafio = false;

        tocarSomVitoria();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_final, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);

        TextView txtResultado = view.findViewById(R.id.txtResultadoFinal);
        Button btnJogarNovamente = view.findViewById(R.id.btnJogarNovamente);

        txtResultado.setText("Você fez " + pontuacao + " pontos! ⭐");

        btnJogarNovamente.setOnClickListener(v -> {
            pontuacao = 0;
            rodadaAtual = 0;
            txtPontuacao.setText("⭐ Pontos: 0");
            dialog.dismiss();
        });
        dialog.show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayerVitoria != null) {
            mediaPlayerVitoria.release();
        }
    }
}