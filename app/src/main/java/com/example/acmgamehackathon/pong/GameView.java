package com.example.acmgamehackathon.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    public Ball ball1;
    public Ball ball2;
    public Bat bat1;


    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        bat1 = new Bat();
        bat1.setVerticalPosition(Position.BOTTOM);

        ball1 = new Ball();
        ball1.setDirection(Direction.UP);
        ball1.setSpeed(Speed.FAST);

        ball2 = new Ball();
        ball2.setDirection(Direction.DOWN);
        ball2.setSpeed(Speed.SLOW);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        while(retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.BLACK);

            bat1.draw(canvas);

            ball1.draw(canvas);
            ball2.draw(canvas);

        }
    }

    public void update() {

        /*
            Might need to place the long if statement in it's own function. No sure if it's
            necessary yet though.
         */

        if(RectF.intersects(ball1.getRect(), bat1.getRect())){
            ball1.reverseYVelocity();

            /*
                Checks if ball hits the right/left edges of the bat.
                Without this, the game will have a glitch where the ball
                will travel ON TOP of the bat if it touches the bat's edge.
             */
            if(RectF.intersects(ball1.getRect(), bat1.getLeftEdge()) ||
                    RectF.intersects(ball1.getRect(), bat1.getRightEdge())) {
                ball1.reverseXVelocity();
            }
        }

        if(RectF.intersects(ball2.getRect(), bat1.getRect())){
            ball2.reverseYVelocity();

            if(RectF.intersects(ball2.getRect(), bat1.getLeftEdge()) ||
                    RectF.intersects(ball2.getRect(), bat1.getRightEdge())) {
                ball2.reverseXVelocity();
            }

        }
        ball1.update();
        ball2.update();
    }


    /*
        Allows users to either touch a specific spot they want the bat to
        move, or drag the bat to whevever they want.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                bat1.setHorizontalPosition(event.getX());
                break;
            case MotionEvent.ACTION_MOVE:
                bat1.setHorizontalPosition(event.getX());
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        return true;
    }



}


