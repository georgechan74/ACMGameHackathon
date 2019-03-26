package com.example.acmgamehackathon.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class BallSprite {
    private float x;
    private float y;
    private float mScreenHeight;
    private float mScreenWidth;
    private float xVelocity;
    private float yVelocity;
    private float ballRadius;

    // SPRITE TUT. Gets the Screen Height/Width
//    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
//    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    // *** MIGHT NOT need these parameters since we're getting the Screen height/Width
    // when a ball object is created (screenWidth and screenHeight)
    public BallSprite(float screenHeight,float screenWidth){
        this.mScreenHeight = screenHeight;
        this.mScreenWidth = screenWidth;
        // Make the mBall size relative to the screen resolution
        ballRadius = screenWidth / 50;

        /*
            Start the ball travelling straight up
            at a quarter of the screen height per second
        */
        this.x = screenWidth / 2;
        this.y = screenHeight / 2;

        // Divide by 4 because ball will travel 1/4th of the screen's size
        yVelocity = screenHeight / 4;
        xVelocity = yVelocity;
    }

    // Change the position each frame
    public void update(long fps){
        y = y + (yVelocity / fps);
        x = x + (xVelocity / fps);

        /*
            Makes sure that the ball doesn't go beyond the width
            and height of the screen.s
         */
        if ((x > mScreenWidth - ballRadius) || (x < ballRadius)) {
            reverseXVelocity();
        }
        if ((y > mScreenHeight - ballRadius) || (y < ballRadius)) {
            reverseYVelocity();
        }
    }

    // Reverse the vertical heading
    public void reverseYVelocity(){
        yVelocity = -yVelocity;
    }

    // Reverse the horizontal heading
    public void reverseXVelocity(){
        xVelocity = -xVelocity;
    }

    // Not too sure what this code does yet.
    // (Found in part 1)
    public void clearObstacleY(float y){
        this.y = y;
    }

//    public void reset(int x, int y){
//        mRect.left = x / 2;
//        mRect.top = y - 20;
//        mRect.right = x / 2 + mBallWidth;
//        mRect.bottom = y - 20 - mBallHeight;
//    }

    public void draw(Canvas canvas) {
        if (canvas != null) {
            Paint paint = new Paint();
            paint.setColor(Color.rgb(250, 0, 0));
            canvas.drawCircle(x, y, ballRadius, paint);
        }
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }
}
