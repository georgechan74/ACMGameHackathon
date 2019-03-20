package com.example.acmgamehackathon.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.acmgamehackathon.MainThread;

import com.example.acmgamehackathon.sprites.BatSprite;
import com.example.acmgamehackathon.sprites.Table;
import com.example.acmgamehackathon.sprites.BallSprite;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread mThread;
    private Table mTable;
    private BallSprite mBall;
    private BatSprite mBat;
    private BatSprite mBat2;
    private int height = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int width = Resources.getSystem().getDisplayMetrics().widthPixels;

    public GameView(Context context,int screenWidth,int screenHeight) {
        super(context);
        getHolder().addCallback(this);
        mThread = new MainThread(getHolder(), this);
        mTable = new Table(height,width);
        mBall = new BallSprite(screenWidth,screenHeight);
        mBat = new BatSprite(screenWidth,screenHeight,1);
        mBat2 = new BatSprite(screenWidth,screenHeight,2);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread.setRunning(true);
        mThread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                mThread.setRunning(false);
                mThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        mTable.draw(canvas);
        mBall.draw(canvas);
        mBat.draw(canvas);
        mBat2.draw(canvas);
    }

    public void update(){
        if (mBall.getX() <= 0) {
            mBall.reverseXVelocity();
            mBall.clearObstacleX(2);
        }
        if (mBall.getX() >= width) {
            mBall.reverseXVelocity();
            mBall.clearObstacleX(width - 22);
        }
        if (mBall.getY() < 0) {
            mBall.reverseYVelocity();
            mBall.clearObstacleY(16);
        }
        if (mBall.getY() > height) {
            mBall.reverseYVelocity();
            mBall.clearObstacleY(height - 2);
        }
        if (mBat.isHit(mBall.getX(),mBall.getY())) {
            mBall.reverseYVelocity();
            mBall.clearObstacleY(mBall.getY() - 2);
        }
        if (mBat2.isHit(mBall.getX(),mBall.getY())) {
            mBall.reverseYVelocity();
            mBall.clearObstacleY(16);
        }
        mBall.update(30);
    }

    public BatSprite getBat() {
        return mBat;
    }

    public BatSprite getBat2() {
        return mBat2;
    }
}
