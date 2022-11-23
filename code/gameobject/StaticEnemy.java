package com.example.theSpartan.gameobject;

import android.content.Context;
import android.graphics.Canvas;

import com.example.theSpartan.GameDisplay;
import com.example.theSpartan.R;
import com.example.theSpartan.tilemap.SpriteSheet;

/**
 * StaticEnemy doesn't move, on collision player loses health
 */
public class StaticEnemy extends GameObject {
    private int k = 0;

    /**
     * constructor matches super
     */
    public StaticEnemy(Context context, double positionX, double positionY, double width, double height) {
        super(context, positionX, positionY, width, height);
        bitmap = createImage(R.drawable.static_enemy);
    }

    // update - draw ///////////////////////////////////////////////////////////////////////////////

    @Override
    public void update() {
        if(k == 0){
            positionX += SpriteSheet.SPRITE_WIDTH_PIXELS/2 - width;
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
