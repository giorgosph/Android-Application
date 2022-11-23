package com.example.theSpartan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.theSpartan.tilemap.Level;

/**
 * MainActivity starts the game
 */
public class MainActivity extends Activity {
    private Game game;
    private static int lvl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        lvl = intent.getIntExtra("lvl", 0);

        // create a new game and set it to the content view
        game = new Game(this);
        setContentView(game);
    }

    @Override
    protected void onPause() {
        game.pause(); // pause the game
        super.onPause();
    }

    /**
     * select the correct layout for the given level
     * @return the layout
     */
    public static int[][] getLevel(){
        int[][] level;
        switch (lvl){
            case 1:
                level = Level.LAYOUT1;
                break;
            case 2:
                level = Level.LAYOUT2;
                break;
            case 3:
                level = Level.LAYOUT3;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + lvl);
        }
        return level;
    }
}
