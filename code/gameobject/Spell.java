package com.example.theSpartan.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.theSpartan.GameDisplay;
import com.example.theSpartan.R;
import com.example.theSpartan.tilemap.SpriteSheet;

import static android.support.v4.content.ContextCompat.getColor;

/**
 * Spells can be used after the player collides with a gun
 * moves to the last x direction the player moved
 * kills any enemy on collision
 */
public class Spell extends GameObject {
    public static final double MULTIPLIER = 3.8;
    private static final double MAX_DISTANCE = 1.65 * SpriteSheet.SPRITE_WIDTH_PIXELS;
    private final double finalPositionMaxX;
    private final double finalPositionMinX;
    private boolean toRemove = false;

    /**
     *constructor
     * @param player - the player that will take its direction from
     */
    public Spell(Context context, Player player) {
        super(context, player.getPositionX(), player.getPositionY(), 20, 20);

        // colour of the spell
        paint = new Paint();
        paint.setColor(getColor(context, R.color.spell));

        // set boundaries
        finalPositionMaxX = positionX + MAX_DISTANCE;
        finalPositionMinX = positionX - MAX_DISTANCE;

        // set velocity according to players direction
        velocityX = player.dSpell * MULTIPLIER;
    }

    // getters - setters ///////////////////////////////////////////////////////////////////////////

    public boolean getToRemove(){
        return toRemove;
    }

    // update - draw ///////////////////////////////////////////////////////////////////////////////

    @Override
    public void update() {
        // remove if a spell reaches the boundaries
        if(positionX > finalPositionMinX && positionX < finalPositionMaxX){
            positionX += velocityX;
            toRemove = false;
        }else{toRemove=true;}
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawCircle(
                (float) gameDisplay.displayCoordinatesX(positionX),
                (float) gameDisplay.displayCoordinatesY(positionY),
                (float) width,
                paint
                );
    }
}
