package com.example.theSpartan.gamepanel;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * right button can be used to move right by tapping it
 */
public class ButtonRight extends Button{
    private final Paint colour;

    /**
     * constructor matching super
     * creates unique colour for the button
     */
    public ButtonRight(int buttonPosX, int buttonPosY, int radius) {
        super(buttonPosX, buttonPosY, radius);

        colour = new Paint();
        colour.setColor(Color.GRAY);
        colour.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(buttonPosX, buttonPosY, radius, colour);
    }
}
