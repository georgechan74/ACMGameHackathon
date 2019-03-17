package com.example.acmgamehackathon;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class GameSession extends AppCompatActivity {

    PongView pongView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();

        // Load the screen resolution into a Point object
        Point size = new Point();
        display.getSize(size);

        // Initialize pongView and set it as the view

        // Passing in the screen height/width when initializing the pongView
        // We will eventually initialize our Bat and Ball objects based on these values
        pongView = new PongView(this, size.x, size.y);
        setContentView(pongView);
    }

    // This method executes when the player starts the game
    @Override
    protected void onResume() {
        super.onResume();

        // Tell the pongView resume method to execute
        pongView.resume();
    }

    // This method executes when the player quits the game
    @Override
    protected void onPause() {
        super.onPause();

        // Tell the pongView pause method to execute
        pongView.pause();
    }
}
