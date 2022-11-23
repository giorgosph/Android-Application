package com.example.theSpartan;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * the thread class that handles the game flow and updates
 */
public class GameLoop extends Thread{
    public static final double MAX_UPS = 30.0;
    private static final double UPS_PERIOD = 1E+3/MAX_UPS;
    private boolean isRunning = false;

    private final Game game;
    private final SurfaceHolder surfaceHolder;

    /**
     * constructor
     * @param game - the current game
     * @param surfaceHolder - surface holder we re using from game
     */
    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    /**
     * starts the thread and the loop
     */
    public void startLoop() {
        isRunning = true;
        start();
    }

    /**
     * updates the game when the thread has start
     */
    @Override
    public void run() {
        super.run();

        // Declare time and cycle count variables
        int updateCount = 0;

        long startTime;
        long elapsedTime;
        long sleepTime;

        // Game loop
        Canvas canvas = null;
        startTime = System.currentTimeMillis();
        while(isRunning) {

            // Try to update and render game
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    game.update();
                    updateCount++;

                    game.draw(canvas);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                if(canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
					} catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
			
            // Pause game loop to not exceed target UPS
            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long) (updateCount*UPS_PERIOD - elapsedTime);
            if(sleepTime > 0) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Skip frames to keep up with target UPS
            while(sleepTime < 0 && updateCount < MAX_UPS-1) {
                game.update();
                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long) (updateCount*UPS_PERIOD - elapsedTime);
            }
        }
    }

    /**
     * stops the thread and the loop
     */
    public void stopLoop() {
        isRunning = false;
        try {
            join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
