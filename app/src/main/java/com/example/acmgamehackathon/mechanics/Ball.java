package com.example.acmgamehackathon.mechanics;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Random;

public class Ball {

    private RectF mRect;
    private float mXVelocity;
    private float mYVelocity;
    private float mBallWidth;
    private float mBallHeight;

    // SPRITE TUT. Gets the Screen Height/Width
//    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
//    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    // *** MIGHT NOT need these parameters since we're getting the Screen height/Width
    // when a ball object is created (screenWidth and screenHeight)
    public Ball(int screenX, int screenY){

        // Make the mBall size relative to the screen resolution
        mBallWidth = screenX / 50;
        mBallHeight = mBallWidth;

        /*
            Start the ball travelling straight up
            at a quarter of the screen height per second
        */

        // Divide by 4 because ball will travel 1/4th of the screen's size
        mYVelocity = screenY / 4;
        mXVelocity = mYVelocity;

        // Initialize the Rect that represents the mBall
        mRect = new RectF();

    }



    // Give access to the Rect
    public RectF getRect(){
        return mRect;
    }

    // Change the position each frame
    public void update(long fps){
        mRect.left = mRect.left + (mXVelocity / fps);

        mRect.top = mRect.top + (mYVelocity / fps);

        mRect.right = mRect.left + mBallWidth;
        mRect.bottom = mRect.top - mBallHeight;


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
        mRect.bottom = y;
        mRect.top = y - mBallHeight;
    }

    public void clearObstacleX(float x){
        mRect.left = x;
        mRect.right = x + mBallWidth;
    }

    public void reset(int x, int y){
        mRect.left = x / 2;
        mRect.top = y - 20;
        mRect.right = x / 2 + mBallWidth;
        mRect.bottom = y - 20 - mBallHeight;
    }
}
