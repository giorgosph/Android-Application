package com.example.theSpartan.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import com.example.theSpartan.R;
import com.example.theSpartan.gameobject.Player;

/**
 * Win draws a message to the user in case of wining
 */
public class Win {
    private final Player player;
    private final Context context;
    private int k = 0;

    /**
     * constructor
     * @param context - current context
     * @param player - the player
     */
    public Win(Context context, Player player) {
        this.context = context;
        this.player = player;
    }

    /**
     * adds points to the player for winning and display the message
     * @param canvas - current canvas
     */
    public void draw(Canvas canvas) {
        if(k == 0){ // only once
            player.addScore(250);
            k++;
        }
        String text = "You Won!!\n";
        String textScore = "Your score is: " + player.getScore();

        float x = 800;
        float y = 200;
        float xs = 650;
        float ys = 355;

        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.win);
        paint.setColor(color);
        float textSize = 150;
        paint.setTextSize(textSize);

        canvas.drawText(text, x, y, paint);

        color = ContextCompat.getColor(context, R.color.winScore);
        paint.setColor(color);
        textSize = 120;
        paint.setTextSize(textSize);

        canvas.drawText(textScore, xs, ys, paint);
    }
}
