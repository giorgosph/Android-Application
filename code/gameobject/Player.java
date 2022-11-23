package com.example.theSpartan.gameobject;

import android.content.Context;
import android.graphics.Canvas;

import com.example.theSpartan.GameDisplay;
import com.example.theSpartan.GameLoop;
import com.example.theSpartan.R;
import com.example.theSpartan.gamepanel.Button;
import com.example.theSpartan.gamepanel.HealthBar;
import com.example.theSpartan.tilemap.Tilemap;

import static com.example.theSpartan.tilemap.SpriteSheet.SPRITE_HEIGHT_PIXELS;
import static com.example.theSpartan.tilemap.SpriteSheet.SPRITE_WIDTH_PIXELS;

/**
 * Player is the GameObject that can be controlled by the user using the 4 buttons
 * It's behaviour/health/score is affected when it collides with another object.
 */
public class Player extends GameObject {
    public static final double SPEED_PIXELS_PER_SECOND = 320.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    public static final int MAX_HEALTH_POINTS = 6;
    private int healthPoints = MAX_HEALTH_POINTS;
    private int score = 0;
    private int keepFight = 0;
    private int wait = 0;
    public double dSpell = MAX_SPEED;

    private final Button leftButton;
    private final Button rightButton;
    private final Button jumpButton;
    private final Button fightButton;
    private final HealthBar healthBar;

    private boolean isFalling = true;
    private boolean toJump = false;
    private boolean toRight = false;
    private boolean toLeft = false;
    private boolean toFight = false;

    private boolean topLeft = true;
    private boolean topRight = true;
    private boolean botLeft = true;
    private boolean botRight = true;

    private boolean gotCoin = false;
    private boolean killEnemy = false;
    private boolean killStaticEnemy = false;
    private boolean killFollowingEnemy = false;
    private boolean doGameOver = false;
    private boolean win = false;
    private boolean gotGun = false;

    /**
     * constructor
     * @param leftButton - button to move left when the user taps on it
     * @param rightButton - button to move right when the user taps on it
     * @param jumpButton  - button to jump when the user taps on it
     * @param fightButton - button to fight or shoot spells when the user taps on it
     */
    public Player(Context context, Button leftButton, Button rightButton, Button jumpButton, Button fightButton,
                  double positionX, double positionY, double width, double height) {
        super(context,  positionX, positionY, width, height);
        this.leftButton = leftButton;
        this.rightButton = rightButton;
        this.jumpButton = jumpButton;
        this.fightButton = fightButton;

        bitmap=createImage(R.drawable.player);

        // Health bar that shows the current health of the player and follows him
        this.healthBar = new HealthBar(context, this);
    }

    /**
     * creates a changing value for gravity
     */
    private void gravityVal(){
        velocityY += GRAVITY;
        if (velocityY > MAX_GRAVITY)
        {
            velocityY = MAX_GRAVITY;
        }
    }

    /**
     * lets the player to move according to the buttons that are pressed
     */
    private void checkDirection() {
        if (jumpButton.getIsPressed()) {
            if(!isFalling){ // player is on ground
                toJump = true;
            }
            jumpButton.setIsPressed(false); // cancels the operation
        }else{toJump = false;}
        if (fightButton.getIsPressed() && !gotGun) {
            toFight = true;
            // cancels the operation (can fight only once per tap)
            fightButton.setIsPressed(false);
        }
        toRight = rightButton.getIsPressed();
        toLeft = leftButton.getIsPressed();
    }

    /**
     * checks the current tile that player moves
     * @param posX - x position of the player
     * @param posY - y position of the player
     */
    private void checkTile(double posX, double posY){
        // convert from tile to pixels on every direction taking into consideration player's size
        int leftTile = (int) ((posX - width) / SPRITE_WIDTH_PIXELS);
        int rightTile = (int) ((posX + width) / SPRITE_WIDTH_PIXELS);
        int topTile = (int) ((posY - height) / SPRITE_HEIGHT_PIXELS);
        int botTile = (int) ((posY + height) / SPRITE_HEIGHT_PIXELS);

        // avoid to use values out of arraylist's size
        if(rightTile < Tilemap.pixelWidth / SPRITE_WIDTH_PIXELS
                && botTile < Tilemap.pixelHeight / SPRITE_HEIGHT_PIXELS) {
            //checks if is a blocking tile
            topLeft = tilemap.tileMap[topTile][leftTile].getIsBlocking();
            topRight = tilemap.tileMap[topTile][rightTile].getIsBlocking();
            botLeft = tilemap.tileMap[botTile][leftTile].getIsBlocking();
            botRight = tilemap.tileMap[botTile][rightTile].getIsBlocking();

            // checks if is a win tile(can only be to positive x direction)
            if(tilemap.tileMap[botTile][rightTile].getType().equals("WIN")
                    || tilemap.tileMap[topTile][rightTile].getType().equals("WIN")){
                win = true;
            }

            //checks if is a death tile
            if(tilemap.tileMap[botTile][rightTile].getType().equals("DEATH")
                    || tilemap.tileMap[botTile][leftTile].getType().equals("DEATH")
                    || tilemap.tileMap[topTile][rightTile].getType().equals("DEATH")
                    || tilemap.tileMap[topTile][leftTile].getType().equals("DEATH")) {

                if(wait < 4){ // every 4 updates
                    if(wait == 0){ // lose 1 time only
                        // player continuously loses health
                        setHealthPoint((getHealthPoint() - 1));
                    }
                    wait++;
                }else{ wait = 0;}
            }

        }
    }

    /**
     * checks for available space for the player to move
     * updates position
     */
    private void tryToMove(){
        double toX = positionX + velocityX;
        double toY = positionY + velocityY;
        double tempX = positionX;
        double tempY = positionY;

        checkTile(positionX, toY);
        if(velocityY < 0){
            if((topLeft || topRight) || (toY < height)){
                velocityY = 0;
                tempY = positionY;
            }else{tempY+=velocityY;}
        }
        if(velocityY > 0){
            if((botLeft || botRight)){
                velocityY = 0;
                isFalling = false;
                tempY -= velocityY;
            }else{
                tempY+=velocityY;
                if(toY > Tilemap.pixelHeight - height){
                    doGameOver = true;
                }
            }
        }

        checkTile(toX, positionY);
        if(velocityX < 0){
            if((topLeft || botLeft) || (toX < width)){
                velocityX = 0;
                tempX = positionX;
            }else{tempX+=velocityX;}
        }
        if(velocityX > 0){
            if((topRight || botRight) || (toX > Tilemap.pixelWidth - width)){
                velocityX = 0;
                tempX = positionX;
            }else{tempX+=velocityX;}
        }

        if(!isFalling){
            checkTile(positionX, positionY + SPRITE_HEIGHT_PIXELS);
            if(!botLeft && !botRight){
                isFalling = true;
            }
        }

        positionX = tempX;
        positionY = tempY;
    }

    // updates player's score on different occasions
    private void calculateScore(){
        if(gotCoin){
            gotCoin = false;
            score += 100;
        }
        if(killEnemy){
            killEnemy = false;
            score += 40;
        }
        if(killStaticEnemy){
            killStaticEnemy = false;
            score += 20;
        }
        if(killFollowingEnemy){
            killFollowingEnemy = false;
            score += 60;
        }
    }

    // getters - setters ///////////////////////////////////////////////////////////////////////////

    public void setGotCoin(boolean bool){
        gotCoin = bool;
    }

    public void setGotGun(boolean bool) {
        gotGun = bool;
    }

    public void setKillEnemy(boolean bool){ killEnemy = bool; }

    public void setKillStaticEnemy(boolean bool){ killStaticEnemy = bool; }

    public void setKillFollowingEnemy(boolean bool) { killFollowingEnemy = bool; }

    public void addScore(int points){ score += points; }

    public void setHealthPoint(int healthPoints) {
        // Accept only positive values
        if (healthPoints >= 0)
            this.healthPoints = healthPoints;
    }

    public boolean getToFight(){ return toFight; }

    public boolean getDoGameOver(){ return doGameOver; }

    public boolean getWin(){ return win; }

    public int getHealthPoint() {
        return healthPoints;
    }

    public int getScore(){ return score; }

    // update - draw ///////////////////////////////////////////////////////////////////////////////

    public void update() {
        // checks direction that player moves
        // changes its velocity correspondingly
        checkDirection();
        if(keepFight<4 && toFight){
            keepFight++;
        }else {
            toFight = false;
            keepFight=0;
        }

        if(toRight){
            velocityX = MAX_SPEED;
        }else if(toLeft){
            velocityX = -MAX_SPEED;
        }else{ velocityX = 0; }

        if(toJump){
            velocityY = -20;
            isFalling = true;
            toJump = false;
        }

        if(isFalling){
            gravityVal();
            toJump=false;
        }

        tryToMove();
        calculateScore();

        // updates direction for spells
        if(velocityX != 0){
            dSpell = velocityX;
        }
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawBitmap(bitmap,
                (float) (gameDisplay.displayCoordinatesX(positionX) - width),
                (float) (gameDisplay.displayCoordinatesY(positionY) - height),
                null);
        healthBar.draw(canvas, gameDisplay);
    }
}
