package com.example.theSpartan.gamepanel;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * fight button can be used by tapping it to fight enemies or shoot spells
 */
public class ButtonFight extends Button {
    private final Paint colour;

    /**
     * constructor matching super
     * creates unique colour for the button
     */
    public ButtonFight(int buttonPosX, int buttonPosY, int radius) {
        super(buttonPosX, buttonPosY, radius);

        colour = new Paint();
        colour.setColor(Color.YELLOW);
        colour.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(buttonPosX, buttonPosY, radius, colour);
    }
}
