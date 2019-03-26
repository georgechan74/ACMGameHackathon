package com.example.acmgamehackathon.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.acmgamehackathon.MainThread;

import com.example.acmgamehackathon.sprites.Table;
import com.example.acmgamehackathon.sprites.BatSprite;
import com.example.acmgamehackathon.sprites.BallSprite;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private float mScreenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private float mScreenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private MainThread mThread;
    private Table mTable;
    private BallSprite mBall;
    private BatSprite[] mBats = new BatSprite[2];

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        mThread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    public void updateGameState(boolean isRunning) {
        if (isRunning) {
            mThread.setRunning(isRunning);
            mThread.start();
        } else {
            try {
                mThread.setRunning(isRunning);
                // stopping the thread would unsafe, so join offers a safer alternative
                mThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mTable = new Table(mScreenHeight, mScreenWidth);
        mBall = new BallSprite(mScreenHeight, mScreenWidth);
        mBats[0] = new BatSprite(mScreenHeight, mScreenWidth, 1);
        mBats[1] = new BatSprite(mScreenHeight, mScreenWidth, 2);

        updateGameState(true);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            updateGameState(false);
            retry = false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        mTable.draw(canvas);
        mBall.draw(canvas);

        for (BatSprite bat : mBats) {
            bat.draw(canvas);
        }
    }

    public void update() {
        for (BatSprite bat : mBats) {
            int hitCode = bat.getHitCode(mBall.getX(),mBall.getY());

            if (hitCode == 1) {
                mBall.reverseXVelocity();
            }
            if (hitCode == 2) {
                mBall.reverseXVelocity();
            }
            if (hitCode == 3) {
                mBall.reverseYVelocity();
            }
            if (hitCode == 4) {
                mBall.reverseYVelocity();
            }
        }
        mBall.update(30);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int count = motionEvent.getPointerCount();
        float player1X;
        float player1Y;
        float player2X;
        float player2Y;

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                player1X = motionEvent.getX(0);
                player1Y = motionEvent.getY(0);

                if (count > 1) {
                    player2X = motionEvent.getX(1);
                    player2Y = motionEvent.getY(1);

                    if(player2Y > mScreenHeight/2){
                        Log.d("Player 1: ", player2X + ", " + player2Y);
                        mBats[0].update(player2X,player2Y);
                    }
                    else {
                        Log.d("Player 2: ", player2X + ", " + player2Y);
                        mBats[1].update(player2X,player2Y);
                    }
                }

                if(player1Y > mScreenHeight/2) {
                    Log.d("Player 1: ", player1X + ", " + player1Y);
                    mBats[0].update(player1X,player1Y);
                }
                else {
                    Log.d("Player 2: ", player1X + ", " + player1Y);
                    mBats[1].update(player1X,player1Y);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                player1X = motionEvent.getX(0);
                player1Y = motionEvent.getY(0);

                if (count > 1) {
                    player2X = motionEvent.getX(1);
                    player2Y = motionEvent.getY(1);

                    if(player2Y > mScreenHeight/2){
                        Log.d("Player 1: ", player2X + ", " + player2Y);
                        mBats[0].update(player2X,player2Y);
                    }
                    else {
                        Log.d("Player 2: ", player2X + ", " + player2Y);
                        mBats[1].update(player2X,player2Y);
                    }
                }
                if(player1Y > mScreenHeight/2) {
                    Log.d("Player 1: ", player1X + ", " + player1Y);
                    mBats[0].update(player1X,player1Y);
                }
                else {
                    Log.d("Player 2: ", player1X + ", " + player1Y);
                    mBats[1].update(player1X,player1Y);
                }
                break;
        }
        return true;
    }
}