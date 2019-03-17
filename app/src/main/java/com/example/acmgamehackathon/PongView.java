package com.example.acmgamehackathon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.acmgamehackathon.mechanics.Ball;
import com.example.acmgamehackathon.mechanics.Bat;


// SurfaceView will allow us to use Thread and implement an onTouchListener
public class PongView extends SurfaceView implements Runnable {
    // This is our thread
    Thread mGameThread = null;

    // We need a SurfaceHolder object
    // We will see it in action in the draw method soon.
    SurfaceHolder mOurHolder;

    // A boolean which we will set and unset
    // when the game is running- or not
    // It is volatile because it is accessed from inside and outside the thread
    volatile boolean mPlaying;

    // Game is mPaused at the start
    boolean mPaused = true;

    // A Canvas and a Paint object
    Canvas mCanvas;
    Paint mPaint;

    // This variable tracks the game frame rate
    long mFPS;

    // The size of the screen in pixels
    int mScreenX;
    int mScreenY;

    // The players mBat
    Bat mBat;


    // A mBall
    Ball mBall;





    // The mScore
    int mScore = 0;

    // Lives
    int mLives = 3;

    /*
        When the we call new() on pongView
        This custom constructor runs
    */
    public PongView(Context context, int x, int y) {

    /*
        The next line of code asks the
        SurfaceView class to set up our object.
    */
        super(context);

        // Set the screen width and height
        mScreenX = x;
        mScreenY = y;

        // Initialize mOurHolder and mPaint objects
        mOurHolder = getHolder();
        mPaint = new Paint();

        // A new mBat
        mBat = new Bat(mScreenX , mScreenY);

        // Create an "AI" bat
        // Might need to create single variable  (centerX, centerY)
        // So we can create both bats with ease




        // Create a mBall
        mBall = new Ball(mScreenX, mScreenY);


        setupAndRestart();
    }


    // Used to setup the entire game
    // We call this method AT THE END of every game to restart it
    public void setupAndRestart(){

        // Put the mBall back to the start
        mBall.reset(mScreenX , mScreenY / 2);

        // if game over reset scores and mLives
        if(mLives == 0) {
            mScore = 0;
            mLives = 3;
        }

    }

    // Everything that needs to be updated goes in here
    // Movement, collision detection etc.
    public void update(){
        // Move the mBat if required
        mBat.update(mFPS);



        mBall.update(mFPS);

        // Check for mBall colliding with mBat
        if(RectF.intersects(mBat.getRect(), mBall.getRect())) {
            mBall.setRandomXVelocity();
            mBall.reverseYVelocity();
            mBall.clearObstacleY(mBat.getRect().top - 2);


            mBall.increaseVelocity();
        }



        // Bounce the mBall back when it hits the bottom of screen
        if(mBall.getRect().bottom > mScreenY){
            mBall.reverseYVelocity();
            mBall.clearObstacleY(mScreenY - 2);

            // Lose a life
            mLives--;


            if(mLives == 0){
                mPaused = true;
                setupAndRestart();
            }
        }

        // Bounce the mBall back when it hits the top of screen
        if(mBall.getRect().top < 0){
            mBall.reverseYVelocity();
            mBall.clearObstacleY(16);
        }



        // If the mBall hits left wall bounce
        if(mBall.getRect().left < 0){
            mBall.reverseXVelocity();
            mBall.clearObstacleX(2);
        }

        if(mBall.getRect().right > mScreenX){
            mBall.reverseXVelocity();
            mBall.clearObstacleX(mScreenX - 22);
        }
    }


    // This is the code that will run on the Thread (Game Loop)
    @Override
    public void run() {
        while (mPlaying) {

            // Capture the current time in milliseconds in startFrameTime
            long startFrameTime = System.currentTimeMillis();

            // Update the frame
            // Update the frame
            // update() is from PongView, not the Bat or Ball
            if(!mPaused){
                update();
            }

            // Draw the frame
            // Will handle all of our Drawing code
            draw();


            // NOT TO SURE WHAT THIS DOES. (PART 4 At bottom)
            // Calculating the time the frame it took to execute
        /*
            Calculate the FPS this frame
            We can then use the result to
            time animations in the update methods.
        */
            long timeThisFrame = System.currentTimeMillis() - startFrameTime;
            // Wrapping mFPS initialization around if-statement because we don't want to divide
            // by 0
            if (timeThisFrame >= 1) {
                mFPS = 1000 / timeThisFrame;
            }

        }

    }

    // If the Activity is paused/stopped
// shutdown our thread.
    public void pause() {
        mPlaying = false;
        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

    }

    // If the Activity starts/restarts
// start our thread.
    public void resume() {
        mPlaying = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }

    // Draw the newly updated scene
    public void draw() {

        // Make sure our drawing surface is valid or we crash
        if (mOurHolder.getSurface().isValid()) {

            // Draw everything here

            // Lock the mCanvas ready to draw
            mCanvas = mOurHolder.lockCanvas();


            mCanvas.drawColor(Color.argb(0, 0, 0, 0));

            // Choose the brush color for drawing
            mPaint.setColor(Color.argb(255, 255, 255, 255));

            // Draw the mBat
            mCanvas.drawRect(mBat.getRect(), mPaint);



            // Draw the mBall
            mCanvas.drawRect(mBall.getRect(), mPaint);


            // Change the drawing color to white
            mPaint.setColor(Color.argb(255, 255, 255, 255));

            // Draw the mScore
            mPaint.setTextSize(40);
            mCanvas.drawText("Score: " + mScore + "   Lives: " + mLives, 10, 50, mPaint);

            // Draw everything to the screen
            mOurHolder.unlockCanvasAndPost(mCanvas);
        }

    }

    // The SurfaceView class implements onTouchListener
// So we can override this method and detect screen touches.
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:

                mPaused = false;

                // Is the touch on the right or left?
                if(motionEvent.getX() > mScreenX / 2){
                    mBat.setMovementState(mBat.RIGHT);

                }
                else{
                    mBat.setMovementState(mBat.LEFT);

                }

                break;

            // Player has removed finger from screen
            case MotionEvent.ACTION_UP:
                mBat.setMovementState(mBat.STOPPED);
                break;
        }
        return true;
    }


}
