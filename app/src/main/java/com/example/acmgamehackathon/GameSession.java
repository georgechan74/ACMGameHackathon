package com.example.acmgamehackathon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.acmgamehackathon.views.GameView;

import com.example.acmgamehackathon.R;

public class GameSession extends AppCompatActivity {
    private GameView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mView = new GameView(this);
        setContentView(mView);
    }
}