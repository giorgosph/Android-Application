package com.example.theSpartan.gamepanel;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * jump button can be used to jump by tapping it
 */
public class ButtonJump extends Button{
    private final Paint colour;

    /**
     * constructor matching super
     * creates unique colour for the button
     */
    public ButtonJump(int buttonPosX, int buttonPosY, int radius) {
        super(buttonPosX, buttonPosY, radius);

        colour = new Paint();
        colour.setColor(Color.BLUE);
        colour.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(buttonPosX, buttonPosY, radius, colour);
    }
}
