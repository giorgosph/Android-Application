package com.example.theSpartan.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import com.example.theSpartan.R;

/**
 * GameOver draws a message to the user in case of losing
 */
public class GameOver {
    private final Context context;

    /**
     * constructor
     * @param context - current context
     */
    public GameOver(Context context) {
        this.context = context;
    }

    /**
     * draws the message
     * @param canvas - current canvas
     */
    public void draw(Canvas canvas) {
        String text = "Game Over";

        float x = 800;
        float y = 200;

        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.gameOver);
        paint.setColor(color);
        float textSize = 150;
        paint.setTextSize(textSize);

        canvas.drawText(text, x, y, paint);
    }
}
