package com.example.acmgamehackathon.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Table {
    private Bitmap image;
    private int height;
    private int width;

    public Table(int screenHeight,int screenWidth) {
        this.height = screenHeight;
        this.width = screenWidth;
    }

    public Table(Bitmap image,int screenHeight,int screenWidth) {
        this.height = screenHeight;
        this.width = screenWidth;
        this.image = image;
    }

    public void draw(Canvas canvas) {
        if (canvas != null) {
            if (image == null) {
                canvas.drawColor(Color.GREEN);
            }
            else {
                canvas.drawBitmap(image, 0, 0, null);
            }
        }
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }
}
