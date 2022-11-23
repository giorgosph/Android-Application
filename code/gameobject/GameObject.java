package com.example.theSpartan.gameobject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.theSpartan.GameDisplay;
import com.example.theSpartan.tilemap.Tilemap;

/**
 * Abstract class that can be implemented by every object in the game
 */
public abstract class GameObject {
    protected static final double GRAVITY = 1.16;
    protected static final double MAX_GRAVITY = 28;

    private final Context context;
    protected double positionX, positionY = 0.0;
    protected double velocityX, velocityY = 0.0;
    protected double directionX = 1.0;
    protected double directionY = 0.0;
    protected double width, height;

    protected Paint paint;
    protected Tilemap tilemap;
    protected Bitmap bitmap;

    /**
     * Constructor assigns object's position and size for later use
     * @param context - current context
     * @param positionX - current x position
     * @param positionY - current y position
     * @param width - width
     * @param height - height
     */
    public GameObject(Context context, double positionX, double positionY, double width, double height) {
        this.width = width;
        this.height = height;
        this.positionX = positionX;
        this.positionY = positionY;
        this.context = context;
    }

    /**
     * getDistanceBetweenObjects returns the distance between two game objects
     * @param obj1 - any object
     * @param obj2 - any object
     * @return double - the current distance between the two objects
     */
    public static double getDistanceBetweenObjects(GameObject obj1, GameObject obj2) {
        return Math.sqrt(
                Math.pow(obj2.getPositionX() - obj1.getPositionX(), 2) +
                        Math.pow(obj2.getPositionY() - obj1.getPositionY(), 2)
        );
    }

    /**
    * checks if two objects touch each other
    * @param obj1 - any object
    * @param obj2 - any object
    * @return boolean - true(touching) otherwise false
    */
    public static boolean isColliding(GameObject obj1, GameObject obj2) {
        double distance = getDistanceBetweenObjects(obj1, obj2);
        double distanceToCollision = obj1.getWidth() + obj2.getWidth();
        return distance < distanceToCollision;
    }

    /**
     * create and resize the image that must be drawn
     * a unique image is declared for different GameObject types
     * @param bitmapID - the image id that must be created
     * @return - the image
     */
    protected Bitmap createImage(int bitmapID) {
        Bitmap btmp = BitmapFactory.decodeResource(context.getResources(), bitmapID);
        return Bitmap.createScaledBitmap(btmp, (int)(width*2), (int)(height*2), false);
    }

    // getters - setters ///////////////////////////////////////////////////////////////////////////

    public double getWidth() {
        return width;
    }

    public double getPositionX() { return positionX; }
    public double getPositionY() { return positionY; }

    public double getDirectionX() { return directionX; }
    public double getDirectionY() { return directionY; }

    public void setTilemap(Tilemap tilemap){
        this.tilemap = tilemap;
    }

    // update - draw ///////////////////////////////////////////////////////////////////////////////

    /**
     * must be implemented by every object to implement changes that must be done during the game
     */
    public abstract void update();

    /**
     * must be implemented by every object to be redrawn after the updates
     * @param canvas - the current canvas
     * @param gameDisplay - the current gameDisplay
     */
    public abstract void draw(Canvas canvas, GameDisplay gameDisplay);
}
