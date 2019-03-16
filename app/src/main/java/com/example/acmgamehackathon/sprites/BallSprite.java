package com.example.acmgamehackathon.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class BallSprite {
    //ADD A BALL IMAGE
    private Bitmap image;

    public BallSprite() {}

    public BallSprite(Bitmap bmp) {
        image = bmp;
    }

    public void draw(Canvas canvas) {
        if (canvas != null) {
            if (image == null) {
                Paint paint = new Paint();
                paint.setColor(Color.rgb(250, 0, 0));
                canvas.drawCircle(100,100,20,paint);
            }
            else {
                canvas.drawBitmap(image, 100, 100, null);
            }
        }
    }
}
