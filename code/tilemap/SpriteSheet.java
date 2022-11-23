package com.example.theSpartan.tilemap;

import android.content.Context;

import com.example.theSpartan.R;

/**
 * assigns the tile types to the tilemap that will be drawn
 */
public class SpriteSheet {
    public static final int NUMBER_OF_SPRITE_ROWS = 10;
    public static final int NUMBER_OF_SPRITE_COLUMNS = 10;
    public static final int SPRITE_WIDTH_PIXELS = 128;
    public static final int SPRITE_HEIGHT_PIXELS = 128;

    /**
     * assigns the sprites to the tile map
     * @param context - current context
     * @return tileMapping - the tile map
     */
    public static Tile[] GetSpriteToTileMapping(Context context) {
        Tile[] tileMapping = new Tile[NUMBER_OF_SPRITE_ROWS*NUMBER_OF_SPRITE_COLUMNS];
        tileMapping[0] = new Tile(context, R.drawable.grass_tile, true, "GRASS");
        tileMapping[1] = new Tile(context, R.drawable.sky_tile, false, "SKY");
        tileMapping[2] = new Tile(context, R.drawable.ground_tile, true, "GROUND");
        tileMapping[3] = new Tile(context, R.drawable.win_tile, false, "WIN");
        tileMapping[4] = new Tile(context, R.drawable.death_tile, false, "DEATH");
        return tileMapping;
    }
}
