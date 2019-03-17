package com.example.acmgamehackathon.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.acmgamehackathon.MainThread;

import com.example.acmgamehackathon.model.Table;
import com.example.acmgamehackathon.sprites.BallSprite;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private Table table;
    private BallSprite ball;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        table = new Table(Resources.getSystem().getDisplayMetrics().heightPixels,Resources.getSystem().getDisplayMetrics().heightPixels);
        ball = new BallSprite();
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
        ball.draw(canvas);
    }

    public void update(){};
}
