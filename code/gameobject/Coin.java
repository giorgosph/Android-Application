package com.example.theSpartan.gameobject;

import android.content.Context;
import android.graphics.Canvas;

import com.example.theSpartan.GameDisplay;
import com.example.theSpartan.R;
import com.example.theSpartan.tilemap.SpriteSheet;

/**
 * coin that gives points to the player.
 */
public class Coin extends GameObject {
    private int k = 0;

    public Coin(Context context, double positionX, double positionY, double width, double height) {
        super(context, positionX, positionY, width, height);
        bitmap = createImage(R.drawable.coin);
    }

    // update - draw ///////////////////////////////////////////////////////////////////////////////

    @Override
    public void update() {
        if(k == 0){ // change only once
            positionX += SpriteSheet.SPRITE_WIDTH_PIXELS/2 - width;
            positionY -= 30;
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
