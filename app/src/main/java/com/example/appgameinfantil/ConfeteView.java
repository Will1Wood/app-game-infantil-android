package com.example.appgameinfantil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class ConfeteView extends View {

    private class Confete {
        float x, y, velocidade;
        int cor;
    }

    private ArrayList<Confete> confetes = new ArrayList<>();
    private Paint paint = new Paint();
    private Random random = new Random();

    public ConfeteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        iniciarConfetes();
    }

    private void iniciarConfetes() {
        post(() -> {
            int largura = getWidth();
            int altura = getHeight();

            for (int i = 0; i < 40; i++) {
                Confete c = new Confete();
                c.x = random.nextInt(largura);
                c.y = random.nextInt(altura);
                c.velocidade = 5 + random.nextInt(10);
                c.cor = 0xFF000000 | random.nextInt(0xFFFFFF);
                confetes.add(c);
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Confete c : confetes) {
            paint.setColor(c.cor);
            canvas.drawCircle(c.x, c.y, 12, paint);

            c.y += c.velocidade;

            if (c.y > getHeight()) {
                c.y = 0;
            }
        }

        invalidate(); // animação contínua
    }
}
