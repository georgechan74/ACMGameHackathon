package com.example.acmgamehackathon.pong;


import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private SurfaceHolder mSurfaceHolder;
    private GameView mGameView;
    private boolean running;
    public static Canvas sCanvas;

    private int targetFPS = 60;
    private double averageFPS;

//    This is going to be our main thread that will create
//    our GameView
    public MainThread(SurfaceHolder holder, GameView gameView) {
        super();

        this.mSurfaceHolder = holder;
        this.mGameView = gameView;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;
        long targetTime = 1000 / targetFPS;

        while (running) {
            startTime = System.nanoTime();
            sCanvas = null;

            try {
                sCanvas = this.mSurfaceHolder.lockCanvas();
                synchronized(mSurfaceHolder) {
                    this.mGameView.update();
                    this.mGameView.draw(sCanvas);
                }
            } catch (Exception e) {       }
            finally {
                if (sCanvas != null)            {
                    try {
                        mSurfaceHolder.unlockCanvasAndPost(sCanvas);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                this.sleep(waitTime);
            } catch (Exception e) {}

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == targetFPS)        {
                averageFPS = 1000 / ((totalTime / frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;

            }
        }
    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }




}
