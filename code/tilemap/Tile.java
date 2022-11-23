package com.example.theSpartan.tilemap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * creates and draws the sprites that will make the map
 */
public class Tile {
    private boolean isBlocking = false;
    private final String type;
    private final Bitmap sprite;

    public Tile(Context context, int spriteID, boolean isBlocking, String type) {
        this.sprite = createImage(context, spriteID);
        this.isBlocking = isBlocking;
        this.type = type;
    }

    // getters /////////////////////////////////////////////////////////////////////////////////////

    public Bitmap getSprite() {
        return sprite;
    }

    public boolean getIsBlocking(){ return isBlocking; }

    public String getType() {
        return type;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * create and resize the image that must be drawn
     * @param bitmapID - the image id that must be created
     * @return - the image
     */
    private Bitmap createImage(Context context, int bitmapID) {
        Bitmap btmp = BitmapFactory.decodeResource(context.getResources(), bitmapID);
        return Bitmap.createScaledBitmap(btmp, 128, 128, false);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(sprite, 20, 20, null);
    }
}
