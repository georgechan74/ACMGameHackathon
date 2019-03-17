package com.example.acmgamehackathon.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.acmgamehackathon.MainThread;

import com.example.acmgamehackathon.sprites.Table;
import com.example.acmgamehackathon.sprites.BallSprite;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private Table table;
    private BallSprite ball;
    private int height = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int width = Resources.getSystem().getDisplayMetrics().widthPixels;

    public GameView(Context context,int screenWidth,int screenHeight) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        table = new Table(height,width);
        ball = new BallSprite(screenWidth,screenHeight);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        table.draw(canvas);
        ball.update(30);
        ball.draw(canvas);
    }

    public void update(){
        if (ball.getX() < 0) {
            ball.reverseXVelocity();
            ball.clearObstacleX(2);
        }
        else if (ball.getX() > width) {
            ball.reverseXVelocity();
            ball.clearObstacleX(width - 22);
        }
        if (ball.getY() < 0) {
            ball.reverseYVelocity();
            ball.clearObstacleY(16);
        }
        else if (ball.getX() > height) {
            ball.reverseYVelocity();
            ball.clearObstacleY(height - 2);
        }
    };
}
