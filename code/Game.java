package com.example.theSpartan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.theSpartan.gameobject.Coin;
import com.example.theSpartan.gameobject.CreateObjectsForLayout;
import com.example.theSpartan.gameobject.FollowingEnemy;
import com.example.theSpartan.gameobject.GameObject;
import com.example.theSpartan.gameobject.Gun;
import com.example.theSpartan.gameobject.MovingEnemyX;
import com.example.theSpartan.gameobject.MovingEnemyY;
import com.example.theSpartan.gameobject.Player;
import com.example.theSpartan.gameobject.Spell;
import com.example.theSpartan.gameobject.StaticEnemy;
import com.example.theSpartan.gamepanel.Button;
import com.example.theSpartan.gamepanel.ButtonFight;
import com.example.theSpartan.gamepanel.ButtonJump;
import com.example.theSpartan.gamepanel.ButtonLeft;
import com.example.theSpartan.gamepanel.ButtonRight;
import com.example.theSpartan.gamepanel.GameOver;
import com.example.theSpartan.gamepanel.Score;
import com.example.theSpartan.gamepanel.Win;
import com.example.theSpartan.tilemap.Level;
import com.example.theSpartan.tilemap.Tilemap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.os.Process.killProcess;
import static android.os.Process.myPid;

/**
 * Game takes everything into consideration
 * makes updates on the game state
 * creates and renders all the GameObjects/Buttons etc..
 */
class Game extends SurfaceView implements SurfaceHolder.Callback {
    private int leftPointerId = 0;
    private int rightPointerId = 0;
    private int jumpPointerId = 0;
    private int fightPointerId = 0;

    private final Button leftButton;
    private final Button rightButton;
    private final Button jumpButton;
    private final Button fightButton;

    private final Player player;
    private GameLoop gameLoop;
    private final GameOver gameOver;
    private final Score score;
    private final GameDisplay gameDisplay;
    private final Tilemap tilemap;
    private final CreateObjectsForLayout createObjects;
    private final Win win;
    private final SurfaceHolder surfaceHolder;
    private Gun gun;

    private List<MovingEnemyX> enemyXList;
    private List<MovingEnemyY> enemyYList;
    private List<StaticEnemy> staticEnemyList;
    private List<Coin> coinList;
    private List<FollowingEnemy> followingEnemyList = new ArrayList<FollowingEnemy>();
    private List<Spell> spellList = new ArrayList<Spell>();

    private int spellNum = 0;
    private boolean boolWin = false;
    private boolean gotGun = false;

    /**
     * constructor initializes everything that will be used in the game
     * @param context - the context that will be using
     */
    public Game(Context context) {
        super(context);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        leftButton = new ButtonLeft(170, 920, 70);
        rightButton = new ButtonRight(350, 920, 70);
        jumpButton = new ButtonJump(1920, 840, 80);
        fightButton = new ButtonFight(1780, 970, 90);

        tilemap = new Tilemap(context, MainActivity.getLevel());

        player = new Player(context, leftButton, rightButton, jumpButton, fightButton, 160, 160, 30, 45);

        score = new Score(context, player);
        gameOver = new GameOver(context);
        win = new Win(context, player);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);

        createObjects = new CreateObjectsForLayout(context, tilemap);
        gun = createObjects.getGun();
        enemyXList = createObjects.getXEnemies();
        enemyYList = createObjects.getYEnemies();
        staticEnemyList = createObjects.getStaticEnemies();
        coinList = createObjects.getCoins();

        setFocusable(true);
    }

    // check...Button //////////////////////////////////////////////////////////////////////////////
    // checks if the button is pressed, saves the pointer id ///////////////////////////////////////
    // @param event - touch event, @param index - 0(first to be pressed) or 1(second) //////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private void checkRightButton(MotionEvent event, int index){
        if (rightButton.isPressed( event.getX(index), event.getY(index))) {
            // store pointer id when is pressed
            rightPointerId = event.getPointerId(index);
            rightButton.setIsPressed(true);
        }
    }

    private void checkLeftButton(MotionEvent event, int index){
        if (leftButton.isPressed( event.getX(index), event.getY(index))) {
            leftPointerId = event.getPointerId(index);
            leftButton.setIsPressed(true);
        }
    }

    private void checkJumpButton(MotionEvent event, int index){
        if (jumpButton.isPressed( event.getX(index), event.getY(index))) {
            jumpPointerId = event.getPointerId(index);
            jumpButton.setIsPressed(true); // will be set to fault in the meanwhile
            jumpButton.setWasPressed(true); // keep tracking if the user keep it pressed

        }
    }

    private void checkFightButton(MotionEvent event, int index){
        if (fightButton.isPressed( event.getX(index), event.getY(index))) {
            fightPointerId = event.getPointerId(index);
            fightButton.setIsPressed(true);
            fightButton.setWasPressed(true);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onTouchEvent(MotionEvent event) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    if (leftButton.getIsPressed()) { // left button already pressed
                        // check for the other's side buttons
                        checkJumpButton(event, 1);
                        checkFightButton(event, 1);
                    } else if (rightButton.getIsPressed()) {
                        checkJumpButton(event, 1);
                        checkFightButton(event, 1);
                    }  else if (jumpButton.getWasPressed()) {
                        checkLeftButton(event, 1);
                        checkRightButton(event, 1);
                    } else if(fightButton.getWasPressed()){
                        checkLeftButton(event, 1);
                        checkRightButton(event, 1);
                    }

                    checkLeftButton(event, 0);
                    checkRightButton(event, 0);
                    checkJumpButton(event, 0);
                    checkFightButton(event, 0);
                    return true;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    if (leftPointerId == event.getPointerId(event.getActionIndex())) {
                        leftButton.setIsPressed(false);
                    }
                    if (rightPointerId == event.getPointerId(event.getActionIndex())) {
                        rightButton.setIsPressed(false);
                    }
                    if (jumpPointerId == event.getPointerId(event.getActionIndex())) {
                        jumpButton.setIsPressed(false);
                        jumpButton.setWasPressed(false);
                    }if (fightPointerId == event.getPointerId(event.getActionIndex())) {
                    fightButton.setIsPressed(false);
                    fightButton.setWasPressed(false);
                    }
                    return true;
            }
        return super.onTouchEvent(event);
    }

    /**
     * initialize holder and GameLoop
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            gameLoop = new GameLoop(this, surfaceHolder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    /**
     * pauses the GameLoop
     */
    public void pause(){
        gameLoop.stopLoop();
    }

    /**
     * destroy the current game and return to LevelSelector
     */
    private void end(){
        try {
            surfaceHolder.wait(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(MainActivity.getLevel() == Level.LAYOUT1) {
            Intent intent = new Intent(getContext(), LevelSelector.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("win1", boolWin);
            if(boolWin) {
                intent.putExtra("score1", player.getScore());
            }
            getContext().startActivity(intent);
        }else if(MainActivity.getLevel() == Level.LAYOUT2) {
            Intent intent = new Intent(getContext(), LevelSelector.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("win2", boolWin);
            if(boolWin) {
                intent.putExtra("score2", player.getScore());
            }
            getContext().startActivity(intent);
        }else if(MainActivity.getLevel() == Level.LAYOUT3) {
            Intent intent = new Intent(getContext(), LevelSelector.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("win3", boolWin);
            if(boolWin) {
                intent.putExtra("score3", player.getScore());
            }
            getContext().startActivity(intent);
        }
        int pid;
        pid = myPid();
        killProcess(pid);
    }

    // check.. /////////////////////////////////////////////////////////////////////////////////////
    // iterates through the ArrayList of the GameObject to check for collision with player /////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private void checkEnemyXCollision(){
        Iterator<MovingEnemyX> iteratorEnemy = enemyXList.iterator();
        while (iteratorEnemy.hasNext()) {
            MovingEnemyX enemy = iteratorEnemy.next();
            if (GameObject.isColliding(enemy, player)) {
                // Remove enemy if it collides with the player
                iteratorEnemy.remove();
                if(player.getToFight()){ // if user tapped the fight button
                    player.setKillEnemy(true); // enemy is killed
                }else {
                    player.setHealthPoint(player.getHealthPoint() - 1); // player loses health
                }
                continue;
            }

            // Remove spell if it collides with an enemy
            if (checkSpell(enemy)) {
                iteratorEnemy.remove();
                player.setKillEnemy(true);
            }
        }
    }

    private void checkEnemyYCollision(){
        Iterator<MovingEnemyY> iteratorEnemy = enemyYList.iterator();
        while (iteratorEnemy.hasNext()) {
            MovingEnemyY enemy = iteratorEnemy.next();
            if (GameObject.isColliding(enemy, player)) {
                iteratorEnemy.remove();
                if(player.getToFight()){
                    player.setKillEnemy(true);
                }else {
                    player.setHealthPoint(player.getHealthPoint() - 1);
                }
                continue;
            }

            if (checkSpell(enemy)) {
                iteratorEnemy.remove();
                player.setKillEnemy(true);
            }
        }
    }

    private void checkFollowingEnemyCollision(){
        Iterator<FollowingEnemy> iteratorEnemy = followingEnemyList.iterator();
        while (iteratorEnemy.hasNext()) {
            FollowingEnemy fEnemy = iteratorEnemy.next();
            if (GameObject.isColliding(fEnemy, player)) {
                iteratorEnemy.remove();
                if(player.getToFight()){
                    player.setKillFollowingEnemy(true);
                }else {
                    player.setHealthPoint(player.getHealthPoint() - 1);
                }
                continue;
            }

            if (checkSpell(fEnemy)) {
                iteratorEnemy.remove();
                player.setKillFollowingEnemy(true);
            }
        }
    }

    private void checkStaticEnemyCollision(){
        Iterator<StaticEnemy> iteratorEnemy = staticEnemyList.iterator();
        while (iteratorEnemy.hasNext()) {
            StaticEnemy sEnemy = iteratorEnemy.next();
            if (GameObject.isColliding(sEnemy, player)) {
                iteratorEnemy.remove();
                if(player.getToFight()){
                    player.setKillStaticEnemy(true);
                }else {
                    player.setHealthPoint(player.getHealthPoint() - 1);
                }
                continue;
            }

            if (checkSpell(sEnemy)) {
                iteratorEnemy.remove();
                player.setKillStaticEnemy(true);
            }
        }
    }


    private void checkCoinCollision(){
        Iterator<Coin> iteratorCoin = coinList.iterator();
        while (iteratorCoin.hasNext()) {
            Coin coin = iteratorCoin.next();
            if (GameObject.isColliding(coin, player)) {
                iteratorCoin.remove();
                player.setGotCoin(true);
                break;
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * checks if a spell collides with an enemy or gets out of boundaries
     * @param enemy
     * @return
     */
    private boolean checkSpell(GameObject enemy){
        boolean ans = false;
        Iterator<Spell> iteratorSpell = spellList.iterator();
        while (iteratorSpell.hasNext()) {
            Spell spell = iteratorSpell.next();
            if(enemy != null) {
                if (GameObject.isColliding(spell, enemy)) {
                    ans = true;
                    iteratorSpell.remove();
                    break;
                }
            }
            if(spell.getToRemove()){
                iteratorSpell.remove();
                break;
            }
        }
        return ans;
    }

    // update - draw ///////////////////////////////////////////////////////////////////////////////

    public void update() {
        if (player.getHealthPoint() <= 0 || player.getDoGameOver()) {
            boolWin = false;
            end();
        }

        if(player.getWin()) {
            boolWin = true;
            end();
        }

        player.setTilemap(tilemap);
        player.update();

        for (MovingEnemyX enemy : enemyXList) {
            enemy.update();
        }

        for (MovingEnemyY enemy : enemyYList) {
            enemy.update();
        }

        if(MainActivity.getLevel() != Level.LAYOUT1) {
            if (FollowingEnemy.readyToSpawn()) {
                followingEnemyList.add(new FollowingEnemy(getContext(), player));
            }
        }

        for (FollowingEnemy fEnemy : followingEnemyList) {
            fEnemy.update();
        }

        for (StaticEnemy sEnemy : staticEnemyList) {
            sEnemy.update();
        }

        for (Coin coin : coinList) {
            coin.update();
        }

        if(gun != null){
            gun.update();
            if (GameObject.isColliding(gun, player)) {
                player.setGotGun(true);
                gotGun = true;
                gun = null;
            }
        }

        // Update states of all spells
        if(fightButton.getIsPressed() && gotGun){
            spellNum++;
            fightButton.setIsPressed(false);
        }
        while (spellNum > 0) {
            spellList.add(new Spell(getContext(), player));
            spellNum --;
        }
        for (Spell spell : spellList) {
            spell.update();
        }

        checkEnemyXCollision();
        checkEnemyYCollision();
        checkFollowingEnemyCollision();
        checkCoinCollision();
        checkStaticEnemyCollision();
        checkSpell(null);

        gameDisplay.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        tilemap.draw(canvas, gameDisplay);
        player.draw(canvas, gameDisplay);

        if(gun != null){
            gun.draw(canvas, gameDisplay);
        }

        for (MovingEnemyX enemy : enemyXList) {
            enemy.draw(canvas, gameDisplay);
        }

        for (MovingEnemyY enemy : enemyYList) {
            enemy.draw(canvas, gameDisplay);
        }

        for (FollowingEnemy fEnemy : followingEnemyList) {
            fEnemy.draw(canvas, gameDisplay);
        }

        for (StaticEnemy sEnemy : staticEnemyList) {
            sEnemy.draw(canvas, gameDisplay);
        }

        for (Coin coin : coinList) {
            coin.draw(canvas, gameDisplay);
        }

        for (Spell spell : spellList) {
            spell.draw(canvas, gameDisplay);
        }

        leftButton.draw(canvas);
        rightButton.draw(canvas);
        jumpButton.draw(canvas);
        fightButton.draw(canvas);
        score.draw(canvas);

        if (player.getHealthPoint() <= 0 || player.getDoGameOver()) {
            gameOver.draw(canvas);
        }

        if(player.getWin()) {
            win.draw(canvas);
        }

    }

}

