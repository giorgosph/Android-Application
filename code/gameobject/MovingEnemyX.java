package com.example.theSpartan.gameobject;

import android.content.Context;
import android.graphics.Canvas;

import com.example.theSpartan.GameDisplay;
import com.example.theSpartan.GameLoop;
import com.example.theSpartan.R;

/**
 * MovingEnemyX moves only on x dimension and in collision player loses health.
 */
public class MovingEnemyX extends GameObject {
    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND*1.1;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final double maxPositionX;
    private final double minPositionX;

    /**
     * constructor
     * @param minPositionX - minimum position that can move
     * @param maxPositionX - maximum position that can move
     */
    public MovingEnemyX(Context context, double minPositionX, double positionY, double maxPositionX, double width, double height) {
        super(context, minPositionX, positionY, width, height);
        velocityX = MAX_SPEED;
        this.maxPositionX = maxPositionX;
        this.minPositionX = minPositionX;
        bitmap = createImage(R.drawable.x_enemy);
    }

    /**
     * updates the current position and velocity
     */
    private void move(){
        if(positionX < minPositionX + 1){
            velocityX = Math.abs(velocityX);
        }else if(positionX > maxPositionX - 1){
            velocityX = Math.abs(velocityX);
            velocityX = -1 * velocityX;
        }

        positionX += velocityX;
    }

    // update - draw ///////////////////////////////////////////////////////////////////////////////

    public void update() {
        move();
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawBitmap(bitmap,
                (float) (gameDisplay.displayCoordinatesX(positionX) - width),
                (float) (gameDisplay.displayCoordinatesY(positionY) - height),
                null);
    }
}

