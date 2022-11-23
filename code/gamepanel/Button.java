package com.example.theSpartan.gamepanel;

import android.graphics.Canvas;

/**
 * abstract class to be implemented by any buttons that the user can use in the game
 */
public abstract class Button {
    protected final int buttonPosX;
    protected final int buttonPosY;
    protected final int radius;
    private boolean isPressed = false;
    private boolean wasPressed = false;

    /**
     * constructor assigns buttons' position and radii for later use
     * @param buttonPosX - x position of button
     * @param buttonPosY - y position of button
     * @param radius - radius of button
     */
    public Button(int buttonPosX, int buttonPosY, int  radius){
        this.buttonPosX = buttonPosX;
        this.buttonPosY = buttonPosY;
        this.radius = radius;
    }

    /**
     * checks if the user taps in the button's area
     * @param positionX - x position of the tap
     * @param positionY - y position of the tap
     * @return boolean - true(if taps the button) otherwise false
     */
    public boolean isPressed(double positionX, double positionY){
        double buttonArea = Math.sqrt(
                Math.pow(buttonPosX - positionX, 2) +
                        Math.pow(buttonPosY - positionY, 2)
        );
        return buttonArea < radius;
    }

    // getters - setters ///////////////////////////////////////////////////////////////////////////

    public boolean getIsPressed(){
       return isPressed;
    }

    public boolean getWasPressed(){
        return wasPressed;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = !wasPressed && isPressed;
    }

    public void setWasPressed(boolean isPressed){
        wasPressed = isPressed;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * abstract function must be implemented to draw the buttons
     * @param canvas - current canvas
     */
    public abstract void draw(Canvas canvas);
}
