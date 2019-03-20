package com.example.acmgamehackathon;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.acmgamehackathon.sprites.BatSprite;
import com.example.acmgamehackathon.views.GameView;

import com.example.acmgamehackathon.R;

public class GameSession extends AppCompatActivity {
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private GameView mView;
    private BatSprite mBat;
    private BatSprite mBat2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mView = new GameView(this,screenWidth,screenHeight);
        mBat = mView.getBat();
        mBat2 = mView.getBat();
        setContentView(mView);

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

                    if(player2Y > screenHeight/2){
                        Log.d("Player 1: ", player2X + ", " + player2Y);
                        mBat.update(player2X,player2Y);
                    }
                    else {
                        Log.d("Player 2: ", player2X + ", " + player2Y);
                        mBat2.update(player2X,player2Y);
                    }
                }

                if(player1Y > screenHeight/2) {
                    Log.d("Player 1: ", player1X + ", " + player1Y);
                    mBat.update(player1X,player1Y);
                }
                else {
                    Log.d("Player 2: ", player1X + ", " + player1Y);
                    mBat2.update(player1X,player1Y);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                player1X = motionEvent.getX(0);
                player1Y = motionEvent.getY(0);

                if (count > 1) {
                    player2X = motionEvent.getX(1);
                    player2Y = motionEvent.getY(1);

                    if(player2Y > screenHeight/2){
                        Log.d("Player 1: ", player2X + ", " + player2Y);
                        mBat.update(player2X,player2Y);
                    }
                    else {
                        Log.d("Player 2: ", player2X + ", " + player2Y);
                        mBat2.update(player2X,player2Y);
                    }
                }
                if(player1Y > screenHeight/2) {
                    Log.d("Player 1: ", player1X + ", " + player1Y);
                    mBat.update(player1X,player1Y);
                }
                else {
                    Log.d("Player 2: ", player1X + ", " + player1Y);
                    mBat2.update(player1X,player1Y);
                }
                break;
        }
        return true;
    }
}