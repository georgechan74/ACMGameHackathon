package com.example.acmgamehackathon.pong;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

enum Direction {
    UP, DOWN
}

enum Speed {
    SLOW, MEDIUM, FAST, WTF
}

public class Ball {


    private int mHeight, mWidth;
    private int x, y;
    private int xVelocity = 10;
    private int yVelocity = 5;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;



    public Ball() {

        /*
            1) Make the Pong size relative to the size of the screen:
                So make mHeight = screenHeight / 100 (100th of screen)
                        mWidth = screenWidth / 25 (25th of the screen)

            2) Using these values we simply use them in our canvas.drawRect()
               to keep the size of the ball consistent.
                    3rd arg would be:
                        x + mWidth
                    4th arg would be:
                        y + mHeight

             3) Make sure you fix this.x and this.y too:
                    Instead use something like
                        x = screenWidth / 2
                        y = screenHeight / 2
                     (Note this wouldn't put ball exactly in the middle but we can fix this
                     with maybe.....

                                x =  (screenWidth / 2) - (mWidth / 2)
                                y = (screenHeight / 2) - (mHeight / 2)

                                These would be the 1st and 2nd args

                       So we would end up with....

                       canvas.drawRect(x, y, x + mWidth, y + mHeight,paint);
                                )
        */

        /*
            The Ball height is 1/50th of the screen height
            and its  width is 1/25th of the screen width.

            These values will be used in drawRect()'s 3rd and 4th
            arguments so we can specify how big we want our rect to be.
         */
        this.mHeight = screenHeight / 50;
        this.mWidth = screenWidth / 25;


        /*
            These values will ensure that the ball starts off
            in the middle of the screen. Having the 1st expression
            would place the ball's top left corner in the middle (which
            is why we subtract the 2nd expression).
         */
        this.x = (screenWidth / 2) - (mWidth / 2);
        this.y = (screenHeight / 2) - (mHeight / 2);
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.rgb(255, 255, 255));

        canvas.drawRect(x, y, x + mWidth, y + mHeight, paint);
    }

    public void setDirection(Direction direction) {
        switch (direction) {
            case UP:
                yVelocity *= -1;
                break;
            case DOWN:
                yVelocity *= 1;
                break;
        }
    }

    public void setSpeed(Speed speed) {
        switch (speed) {
            case SLOW:
                xVelocity /= 2;
                yVelocity /= 2;
                break;
            case MEDIUM:
                xVelocity *= 2;
                yVelocity *= 2;
                break;
            case FAST:
                xVelocity *= 3;
                yVelocity *= 3;
                break;
            case WTF:
                xVelocity *= 5;
                yVelocity *= 5;
                break;
        }
    }

    public void reverseYVelocity() {
        this.yVelocity *= -1;
    }

    public void reverseXVelocity() {
        this.xVelocity *= -1;
    }

    public RectF getRect() {
        return new RectF(x, y, x + mWidth, y + mHeight);
    }

    // So ball doesn't get stuck on bat
    public void clearObstacle(float y) {

       this.y = (int) y + mHeight;
//        mRect.top = y - mBallHeight;
    }

    public void update() {
        x += xVelocity;
        y += yVelocity;


        /*
            Makes sure that the ball doesn't go beyond the width
            and height of the screen.
         */
        if ((x > screenWidth - this.mWidth) || (x < 0)) {
            xVelocity = xVelocity * -1;
        }
        if ((y > screenHeight - this.mHeight) || (y < 0)) {
            yVelocity = yVelocity * -1;
        }


        // If img reaches top/bottom then we increase the speed (Somewhat like pong?)
//        if(y < 0 || y > screenHeight - this.mHeight) {
//            yVelocity = yVelocity * 2;
//
//            // Reset the settings if figure gets too fast
//            if(yVelocity > 20) {
//                yVelocity = 5;
//                // *** Crucial! Need to reset y because that's what is actually
//                // being used to draw the bitmap!
//
//                // If you don't then bitmap will maintain it's high y value
//                // and yVelocity will continue to INCREMENT y
//                y = 100;
//            }
//        }
    }
}
