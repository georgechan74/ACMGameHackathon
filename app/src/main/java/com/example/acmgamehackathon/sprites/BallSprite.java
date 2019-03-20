package com.example.acmgamehackathon.sprites;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Random;

public class BallSprite {
    private float x;
    private float y;
    private float mXVelocity;
    private float mYVelocity;
    private float mBallRadius;

    // SPRITE TUT. Gets the Screen Height/Width
//    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
//    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    // *** MIGHT NOT need these parameters since we're getting the Screen height/Width
    // when a ball object is created (screenWidth and screenHeight)
    public BallSprite(int screenWidth, int screenHeight){

        // Make the mBall size relative to the screen resolution
        mBallRadius = screenWidth / 50;

        /*
            Start the ball travelling straight up
            at a quarter of the screen height per second
        */

        // Divide by 4 because ball will travel 1/4th of the screen's size
        mYVelocity = screenHeight / 4;
        mXVelocity = mYVelocity;
    }

    // Change the position each frame
    public void update(long fps){
        y = y + (mYVelocity / fps);
        x = x + (mXVelocity / fps);
    }

    // Reverse the vertical heading
    public void reverseYVelocity(){
        mYVelocity = -mYVelocity;
    }

    // Reverse the horizontal heading
    public void reverseXVelocity(){
        mXVelocity = -mXVelocity;
    }

    public void setRandomXVelocity(){
        // Generate a random number either 0 or 1
        Random generator = new Random();
        int answer = generator.nextInt(2);

        if(answer == 0){
            reverseXVelocity();
        }
    }

    // Speed up by 10%
    // A score of over 20 is quite difficult
    // Reduce or increase 10 to make this easier or harder
    public void increaseVelocity(){
        mXVelocity = mXVelocity + mXVelocity / 10;
        mYVelocity = mYVelocity + mYVelocity / 10;
    }

    // Not too sure what this code does yet.
    // (Found in part 1)
    public void clearObstacleY(float y){
        this.y = y;
    }

    public void clearObstacleX(float x){
        this.x = x;
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
            canvas.drawCircle(x, y, mBallRadius, paint);
        }
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }
}
