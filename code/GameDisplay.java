package com.example.theSpartan;

import android.graphics.Rect;

import com.example.theSpartan.gameobject.GameObject;

/**
 * displays a part of the map with the player centered
 */
public class GameDisplay {
    public final int WIDTH_PIXELS;
    public final int HEIGHT_PIXELS;
    private final GameObject centerObject;
    private final double displayCenterX;
    private final double displayCenterY;
    private double offsetX, offsetY, gameCenterX, gameCenterY;
    private Rect displayRect;
    public final Rect RECT_ORIGIN;

    /**
     * constructor
     * @param widthPixels - width
     * @param heightPixels - height
     * @param centerObject - the GameObject to be centered(player)
     */
    public GameDisplay(int widthPixels, int heightPixels, GameObject centerObject) {
        this.WIDTH_PIXELS = widthPixels;
        this.HEIGHT_PIXELS = heightPixels;
        this.centerObject = centerObject;

        displayCenterX = widthPixels/2.0;
        displayCenterY = heightPixels/2.0;

        displayRect = new Rect();
        RECT_ORIGIN = new Rect(0, 0, widthPixels, heightPixels);

        update();
    }

    public double displayCoordinatesX(double x) {
        return x + offsetX;
    }

    public double displayCoordinatesY(double y) {
        return y + offsetY;
    }

    public Rect getDisplayRect() {
        return displayRect;
    }

    public void update() {
        gameCenterX = centerObject.getPositionX();
        gameCenterY = centerObject.getPositionY();

        displayRect.set(
                (int) (gameCenterX - displayCenterX),
                (int) (gameCenterY - displayCenterY),
                (int) (gameCenterX + displayCenterX),
                (int) (gameCenterY + displayCenterY)
        );

        offsetX = displayCenterX - gameCenterX;
        offsetY = displayCenterY - gameCenterY;
    }
}
