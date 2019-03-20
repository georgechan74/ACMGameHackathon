package com.example.acmgamehackathon.pong;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

enum Position{
    TOP, BOTTOM
}

public class Bat {

    private int mHeight, mWidth;
    private int x, y;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public Bat() {

        this.mWidth = screenWidth / 3;
        this.mHeight = screenHeight / 50;

        this.x = (screenWidth / 2) - (mWidth / 2);
        // Not sure if this will work out...
        // But I'm hoping this will push the
//        this.y = screenHeight - (mHeight * 2);
    }

    public void setVerticalPosition(Position position) {
        switch (position){
            case TOP:
                this.y = mHeight * 5;
                break;
            case BOTTOM:
                this.y = screenHeight - (mHeight * 5);
                break;
        }
    }

    public void setHorizontalPosition(float x) {
        this.x = (int) x - (mWidth / 2);
    }

    public RectF getRect() {
        return new RectF(x, y, x + mWidth, y + mHeight);
    }

    public RectF getLeftEdge() {
        // x is used twice bc we our rectangle is going to be extremely thin (edge)
        return new RectF(x, y, x, y + mHeight);
    }

    public RectF getRightEdge() {
        return new RectF(x + mWidth, y, x + mWidth, y +mHeight);
    }


    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(255, 255, 255)); // White

        canvas.drawRect(x, y, x + mWidth, y + mHeight, paint);
    }
}
