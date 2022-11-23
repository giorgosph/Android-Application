package com.example.theSpartan.gameobject;

import android.content.Context;
import android.graphics.Canvas;

import com.example.theSpartan.GameDisplay;
import com.example.theSpartan.GameLoop;
import com.example.theSpartan.R;
import com.example.theSpartan.tilemap.SpriteSheet;

/**
 * MovingEnemyY moves only on y dimension and in collision player loses health.
 */
public class MovingEnemyY extends GameObject {
    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND*1.5;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final double maxPositionY;
    private final double minPositionY;
    private int k = 0;

    /**
     * constructor
     * @param minPositionY - minimum position that can move
     * @param maxPositionY - maximum position that can move
     */
    public MovingEnemyY(Context context, double positionX, double minPositionY, double maxPositionY, double width, double height) {
        super(context, positionX, minPositionY, width, height);
        velocityY = MAX_SPEED;
        this.maxPositionY = maxPositionY;
        this.minPositionY = minPositionY;
        bitmap = createImage(R.drawable.x_enemy);
    }

    /**
     * updates the current position and velocity
     */
    private void move(){
        if(positionY < maxPositionY  + 1){
            velocityY = Math.abs(velocityY);
        }else if(positionY > minPositionY - 1){
            velocityY = Math.abs(velocityY);
            velocityY = -1 * velocityY;
        }

        positionY += velocityY;
    }

    // update - draw ///////////////////////////////////////////////////////////////////////////////

    @Override
    public void update() {
        move();
        if(k == 0){ // change only once
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
