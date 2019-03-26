package com.example.acmgamehackathon.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

public class BatSprite {
    private float screenHeight;
    // How long and high our mBat will be
    private float batWidth;
    private float batHeight;

    // x and y will be at the center of the rectangle which forms our mBat
    public float x;
    public float y;

    // This is the constructor method
    // When we create an object from this class we will pass
    // in the screen width and mHeight
    public BatSprite(float screenHeight,float screenWidth,int playerNum) {
        this.screenHeight = screenHeight;
        // 1/8 screen width wide
        this.batWidth = screenWidth / 4;
        // 1/50 screen mHeight high
        this.batHeight = screenHeight / 50;

        // Start mBat in roughly the screen center
        this.x = screenWidth / 2;

        if (playerNum == 2) {
            this.y = screenHeight/6;
        }
        else {
            this.y = screenHeight - (screenHeight / 6);
        }
    }

    public void draw(Canvas canvas) {
        if (canvas != null) {
            Paint paint = new Paint();
            paint.setColor(Color.rgb(100, 100, 200));
            float left = x - (batWidth/2);
            float top = y - (batHeight/2);
            canvas.drawRect(left,top,left + batWidth,top + batHeight,paint);
        }
    }

    // Update the Bat graphics
    public void update(float x, float y){
        if (y > screenHeight/2) {
            this.x = x;
            this.y = y - 20;
        }
        else {
            this.x = x;
            this.y = y - 20;
        }
    }
    // will return an int that represents the side of the bat: 1 (left), 2 (right), 3 (top), 4 (bottom)
    public int getHitCode(float ballX, float ballY) {
        double hitOffset = 0.5;
        double top = y - (batHeight / 2);
        double bottom = y + (batHeight / 2);
        double left = x - (batWidth / 2);
        double right = x + (batWidth / 2);

        //first layor is considering the x coordinate
        if (ballX < left && ballX > left - hitOffset) {
            if (ballY > top && ballY < bottom) {
                return 1;
            }
        }
        if (ballX > right && ballX < right + hitOffset) {
            if (ballY > top && ballY < bottom) {
                return 2;
            }
        }
        if (ballY < top && ballY > top - hitOffset) {
            if (ballX > left && ballX < right) {
                return 3;
            }
        }
        if (ballY > bottom && ballY < bottom + hitOffset) {
            if (ballX > left && ballX < right) {
                return 4;
            }
        }
        return 0;
    }
}