package com.example.theSpartan.gameobject;

import android.content.Context;
import android.graphics.Canvas;

import com.example.theSpartan.GameDisplay;
import com.example.theSpartan.GameLoop;
import com.example.theSpartan.R;

/**
 * FollowingEnemy is always following the player and in collision player loses health.
 * It appears in random direction within a range.
 */
public class FollowingEnemy extends GameObject {
    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND * 0.95;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private static final double SPAWNS_PER_MINUTE = 6.5;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE/60.0;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double UPDATES_UNTIL_NEXT_SPAWN = UPDATES_PER_SPAWN;
    private final Player player;

    /**
     * Constructor
     * @param player - object that must follow
     */
    public FollowingEnemy(Context context, Player player) {
        super(context, Math.random()*1000, Math.random()*1000, 30, 30);
        this.player = player;
        bitmap = createImage(R.drawable.folowing_enemy);
    }

    /**
     * checks if enough time has passed for the next enemy to be spawned.
     * @return boolean - true (to spawn) otherwise false
     */
    public static boolean readyToSpawn() {
        if (UPDATES_UNTIL_NEXT_SPAWN <= 0) {
            UPDATES_UNTIL_NEXT_SPAWN += UPDATES_PER_SPAWN;
            return true;
        } else {
            UPDATES_UNTIL_NEXT_SPAWN --;
            return false;
        }
    }

    // update - draw ///////////////////////////////////////////////////////////////////////////////

    public void update() {
        //calculate the current distance to the player of all dimensions
        double distanceToPlayerX = player.getPositionX() - positionX;
        double distanceToPlayerY = player.getPositionY() - positionY;

        // calculate (absolute) distance to player
        double distanceToPlayer = GameObject.getDistanceBetweenObjects(this, player);

        // calculate direction of all dimensions
        double directionX = distanceToPlayerX/distanceToPlayer;
        double directionY = distanceToPlayerY/distanceToPlayer;

        // update velocity according to the player's
        if(distanceToPlayer > 0) {
            velocityX = directionX*MAX_SPEED;
            velocityY = directionY*MAX_SPEED;
        } else {
            velocityX = 0;
            velocityY = 0;
        }

        // update current position
        positionX += velocityX;
        positionY += velocityY;
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawBitmap(bitmap,
                (float) (gameDisplay.displayCoordinatesX(positionX) - width),
                (float) (gameDisplay.displayCoordinatesY(positionY) - height),
                null);
    }
}
