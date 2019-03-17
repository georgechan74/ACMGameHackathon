package com.example.acmgamehackathon.sprites;

import android.graphics.RectF;

public class BatSprite {

    // RectF is an object that holds four coordinates
    private RectF mRect;

    // How long and high our mBat will be
    private float mLength;
    private float mHeight;

    // X is the far left of the rectangle which forms our mBat
    // Far-LEFT coord
    private float mXCoord;

    // Y is the top coordinate
    private float mYCoord;

    // This will hold the pixels per second speed that
    // the mBat will move
    private float mBatSpeed;

    // Which ways can the mBat move
    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT = 2;

    // The screen length and width in pixels
    private int mScreenX;
    private int mScreenY;

    // This is the constructor method
    // When we create an object from this class we will pass
    // in the screen width and mHeight
    public BatSprite(int x, int y) {

        mScreenX = x;
        mScreenY = y;

        // 1/8 screen width wide
        mLength = mScreenX / 8;

        // 1/50 screen mHeight high
        mHeight = mScreenY / 50;

        // Start mBat in roughly the screen center
        mXCoord = mScreenX / (float)2.5;
        mYCoord = mScreenY / (float)1.2;

        mRect = new RectF(mXCoord, mYCoord, mXCoord + mLength, mYCoord + mHeight);

        // How fast is the mBat in pixels per second
        mBatSpeed = mScreenX;
        // Cover entire screen in 1 second
    }

    // This is a getter method to make the rectangle that
    // defines our bat available in PongView class
    public RectF getRect(){
        return mRect;
    }

    // Update the Bat graphics
    public void update(int x, int y){
        mRect.left = x;
        mRect.right = x + mLength;
        mRect.top = y + mHeight;
        mRect.bottom = y;
    }
}