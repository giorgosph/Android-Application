package com.example.theSpartan.gameobject;

import android.content.Context;
import android.graphics.Canvas;

import com.example.theSpartan.GameDisplay;
import com.example.theSpartan.R;
import com.example.theSpartan.tilemap.SpriteSheet;

/**
 * gun that lets the player shoot bullets for the rest of the game play
 */
public class Gun extends GameObject {
    private int k = 0;

    /**
     * constructor matching super
     */
    public Gun(Context context, double positionX, double positionY, double width, double height) {
        super(context, positionX, positionY, width, height);
        bitmap = createImage(R.drawable.gun);
    }

    // update - draw ///////////////////////////////////////////////////////////////////////////////

    @Override
    public void update() {
        if (k == 0) { // change only once
            positionX += SpriteSheet.SPRITE_WIDTH_PIXELS / 2 - width;
            k++;
        }
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawBitmap(bitmap,
                (float) (gameDisplay.displayCoordinatesX(positionX) - width),
                (float) (gameDisplay.displayCoordinatesY(positionY) - height),
                null);
    }
}
