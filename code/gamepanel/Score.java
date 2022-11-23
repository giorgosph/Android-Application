package com.example.theSpartan.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import com.example.theSpartan.R;
import com.example.theSpartan.gameobject.Player;

/**
 * Score displays the current score to the screen
 */
public class Score {
    private final Context context;
    private final Player player;

    /**
     *constructor
     * @param context - current context
     * @param player - player that will be used for getting the current score
     */
    public Score(Context context, Player player) {
        this.context = context;
        this.player = player;
    }

    /**
     * draws the current score to the screen during the game
     * @param canvas - current canvas
     */
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("Score: " + player.getScore(), 100, 100, paint);
    }
}
