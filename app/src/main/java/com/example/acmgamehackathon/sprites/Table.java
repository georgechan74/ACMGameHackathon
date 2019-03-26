package com.example.acmgamehackathon.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Table {
    private Bitmap image;
    private float height;
    private float width;

    public Table(float screenHeight,float screenWidth) {
        this.height = screenHeight;
        this.width = screenWidth;
    }

    public Table(Bitmap image,float screenHeight,float screenWidth) {
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
}
