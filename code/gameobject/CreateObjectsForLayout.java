package com.example.theSpartan.gameobject;

import android.content.Context;

import com.example.theSpartan.tilemap.Level;
import com.example.theSpartan.tilemap.SpriteSheet;
import com.example.theSpartan.tilemap.Tilemap;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates the GameObjects for different levels
 */
public class CreateObjectsForLayout {
    private final int[][] tileLayout;
    private final Context context;
    private List<MovingEnemyX> enemyXList = new ArrayList<MovingEnemyX>();
    private List<MovingEnemyY> enemyYList = new ArrayList<MovingEnemyY>();
    private List<StaticEnemy> staticEnemyList = new ArrayList<StaticEnemy>();
    private List<Coin> coinList = new ArrayList<Coin>();
    private Gun gun = null;

    /**
     * Constructor
     * @param context - current context
     * @param tilemap - tilemap of the current level
     */
    public CreateObjectsForLayout(Context context, Tilemap tilemap){
        tileLayout = tilemap.getTileLayout();
        this.context = context;

        createObjects();
    }

    /**
     * check the current level and create all the objects on the tiles we want
     * give them sizes
     */
    private void createObjects(){
        if(tileLayout == Level.LAYOUT1){

            enemyXList.add(new MovingEnemyX(context, tileToPixelX(9, 30), tileToPixelY(11, 30), tileToPixelMaxX(12, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(7,30), tileToPixelY(3, 30), tileToPixelMaxX(10, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(34, 30), tileToPixelY(11, 30), tileToPixelMaxX(36, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(33, 30), tileToPixelY(4, 30), tileToPixelMaxX(34, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(67, 30), tileToPixelY(13, 30), tileToPixelMaxX(69, 30), 30, 30));

            staticEnemyList.add(new StaticEnemy(context, tileToPixelX(13, 28), tileToPixelY(10, 28), 28, 28));
            staticEnemyList.add(new StaticEnemy(context, tileToPixelX(16, 28), tileToPixelY(8, 28), 28,28));

            coinList.add(new Coin(context, tileToPixelX(4, 20), tileToPixelY(5, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(8, 20), tileToPixelY(8, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(15, 20), tileToPixelY(6, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(17, 20), tileToPixelY(5, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(17, 20), tileToPixelY(2, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(23, 20), tileToPixelY(9, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(25, 20), tileToPixelY(1, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(35, 20), tileToPixelY(7, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(36, 20), tileToPixelY(4, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(60, 20), tileToPixelY(6, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(66, 20), tileToPixelY(7, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(72, 20), tileToPixelY(8, 20), 20, 20));

        }else if(tileLayout == Level.LAYOUT2){

            enemyXList.add(new MovingEnemyX(context, tileToPixelX(9, 30), tileToPixelY(7, 30), tileToPixelMaxX(12, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(4, 30), tileToPixelY(11, 30), tileToPixelMaxX(7, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(1, 30), tileToPixelY(14, 30), tileToPixelMaxX(6, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(4, 30), tileToPixelY(14, 30), tileToPixelMaxX(10, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(11, 30), tileToPixelY(13, 30), tileToPixelMaxX(14, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(16, 30), tileToPixelY(4, 30), tileToPixelMaxX(19, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(17, 30), tileToPixelY(2, 30), tileToPixelMaxX(20, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(19, 30), tileToPixelY(2, 30), tileToPixelMaxX(23, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(24, 30), tileToPixelY(6, 30), tileToPixelMaxX(26, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(23, 30), tileToPixelY(8, 30), tileToPixelMaxX(28, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(21, 30), tileToPixelY(10, 30), tileToPixelMaxX(23, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(12, 30), tileToPixelY(15, 30), tileToPixelMaxX(13, 30), 30, 30));

            enemyYList.add(new MovingEnemyY(context, tileToPixelX(19, 30), tileToPixelY(14, 30), tileToPixelMaxY(10, 30), 30, 30));
            enemyYList.add(new MovingEnemyY(context, tileToPixelX(20, 30), tileToPixelY(13, 30), tileToPixelMaxY(10, 30), 30, 30));
            enemyYList.add(new MovingEnemyY(context, tileToPixelX(25, 30), tileToPixelY(14, 30), tileToPixelMaxY(11, 30), 30, 30));

            coinList.add(new Coin(context, tileToPixelX(12, 20), tileToPixelY(3, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(11, 20), tileToPixelY(7, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(9, 20), tileToPixelY(7, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(9, 20), tileToPixelY(12, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(27, 20), tileToPixelY(5, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(29, 20), tileToPixelY(3, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(35, 20), tileToPixelY(12, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(34, 20), tileToPixelY(8, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(32, 20), tileToPixelY(7, 20), 20, 20));

            gun = new Gun(context, tileToPixelX(15, 28), tileToPixelY(9, 28), 28, 28);

        }else if(tileLayout == Level.LAYOUT3){
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(6, 30), tileToPixelY(14, 30), tileToPixelMaxX(10, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(23, 30), tileToPixelY(8, 30), tileToPixelMaxX(28, 30), 30, 30));
            enemyXList.add(new MovingEnemyX(context, tileToPixelX(33, 30), tileToPixelY(16, 30), tileToPixelMaxX(35, 30), 30, 30));

            enemyYList.add(new MovingEnemyY(context, tileToPixelX(43, 30), tileToPixelY(14, 30), tileToPixelMaxY(11, 30), 30, 30));
            enemyYList.add(new MovingEnemyY(context, tileToPixelX(45, 30), tileToPixelY(14, 30), tileToPixelMaxY(11, 30), 30, 30));
            enemyYList.add(new MovingEnemyY(context, tileToPixelX(47, 30), tileToPixelY(14, 30), tileToPixelMaxY(11, 30), 30, 30));
            enemyYList.add(new MovingEnemyY(context, tileToPixelX(48, 30), tileToPixelY(14, 30), tileToPixelMaxY(11, 30), 30, 30));
            enemyYList.add(new MovingEnemyY(context, tileToPixelX(47, 30), tileToPixelY(17, 30), tileToPixelMaxY(10, 30), 30, 30));
            enemyYList.add(new MovingEnemyY(context, tileToPixelX(48, 30), tileToPixelY(17, 30), tileToPixelMaxY(10, 30), 30, 30));

            staticEnemyList.add(new StaticEnemy(context, tileToPixelX(7, 28), tileToPixelY(7, 28), 28, 28));
            staticEnemyList.add(new StaticEnemy(context, tileToPixelX(11, 28), tileToPixelY(10, 28), 28, 28));
            staticEnemyList.add(new StaticEnemy(context, tileToPixelX(30, 28), tileToPixelY(12, 28), 28, 28));
            staticEnemyList.add(new StaticEnemy(context, tileToPixelX(59, 28), tileToPixelY(4, 28), 28, 28));

            coinList.add(new Coin(context, tileToPixelX(10, 20), tileToPixelY(12, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(8, 20), tileToPixelY(7, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(21, 20), tileToPixelY(7, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(22, 20), tileToPixelY(9, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(28, 20), tileToPixelY(8, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(27, 20), tileToPixelY(5, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(30, 20), tileToPixelY(4, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(25, 20), tileToPixelY(6, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(23, 20), tileToPixelY(2, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(20, 20), tileToPixelY(2, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(26, 20), tileToPixelY(8, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(48, 20), tileToPixelY(2, 20), 20, 20));
            coinList.add(new Coin(context, tileToPixelX(52, 20), tileToPixelY(2, 20), 20, 20));

            gun = new Gun(context, tileToPixelX(17, 10), tileToPixelY(10, 28), 28, 28);

        }
    }

    // tileToPixel.. ///////////////////////////////////////////////////////////////////////////////
    // Converts tiles to pixels to place the objects on specific positions considering their size //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private double tileToPixelX(int tile, double width){
        return (tile-1) * SpriteSheet.SPRITE_WIDTH_PIXELS + width;
    }
    private double tileToPixelMaxX(int tile, double width){
        return tile * SpriteSheet.SPRITE_WIDTH_PIXELS - width;
    }
    private double tileToPixelY(int tile, double radius){
        return tile * SpriteSheet.SPRITE_HEIGHT_PIXELS - radius;
    }

    private double tileToPixelMaxY(int tile, double height) {
        return (tile-1) * SpriteSheet.SPRITE_HEIGHT_PIXELS + height;
    }

    // getters /////////////////////////////////////////////////////////////////////////////////////

    public Gun getGun(){return gun;}

    public List<MovingEnemyX> getXEnemies(){
        return enemyXList;
    }

    public List<MovingEnemyY> getYEnemies(){
        return enemyYList;
    }

    public List<Coin> getCoins(){
        return coinList;
    }

    public List<StaticEnemy> getStaticEnemies() { return staticEnemyList; }
}
