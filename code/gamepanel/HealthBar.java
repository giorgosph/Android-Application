package com.example.theSpartan.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;

import com.example.theSpartan.GameDisplay;
import com.example.theSpartan.R;
import com.example.theSpartan.gameobject.Player;

/**
 * HealthBar shows to the user the players health
 */
public class HealthBar {
    private final Player player;
    private final Paint borderPaint;
    private final Paint healthPaint;
    private final int width;
    private final int height;
    private final int margin;

    /**
     * constructor creates colours for health bar
     * set sizes
     * @param context - current context
     * @param player - the player that will take information from
     */
    public HealthBar(Context context, Player player) {
        this.player = player;
        this.width = 100;
        this.height = 20;
        this.margin = 2;

        this.borderPaint = new Paint();
        int borderColor = ContextCompat.getColor(context, R.color.healthBarBorder);
        borderPaint.setColor(borderColor);

        this.healthPaint = new Paint();
        int healthColor = ContextCompat.getColor(context, R.color.healthBarHealth);
        healthPaint.setColor(healthColor);
    }

    /**
     * draws the health bar to the screen
     * @param canvas - current canvas
     * @param gameDisplay - current gameDisplay
     */
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        float x = (float) player.getPositionX();
        float y = (float) player.getPositionY();
        float distanceToPlayer = 50;
        float healthPointPercentage = (float) player.getHealthPoint()/player.MAX_HEALTH_POINTS;

        // Draw border
        float borderLeft, borderTop, borderRight, borderBottom;
        borderLeft = x - width/2;
        borderRight = x + width/2;
        borderBottom = y - distanceToPlayer;
        borderTop = borderBottom - height;
        canvas.drawRect(
                (float) gameDisplay.displayCoordinatesX(borderLeft),
                (float) gameDisplay.displayCoordinatesY(borderTop),
                (float) gameDisplay.displayCoordinatesX(borderRight),
                (float) gameDisplay.displayCoordinatesY(borderBottom),
                borderPaint);

        // Draw health
        float healthLeft, healthTop, healthRight, healthBottom, healthWidth, healthHeight;
        healthWidth = width - 2*margin;
        healthHeight = height - 2*margin;
        healthLeft = borderLeft + margin;
        healthRight = healthLeft + healthWidth*healthPointPercentage;
        healthBottom = borderBottom - margin;
        healthTop = healthBottom - healthHeight;
        canvas.drawRect(
                (float) gameDisplay.displayCoordinatesX(healthLeft),
                (float) gameDisplay.displayCoordinatesY(healthTop),
                (float) gameDisplay.displayCoordinatesX(healthRight),
                (float) gameDisplay.displayCoordinatesY(healthBottom),
                healthPaint);
    }
}
