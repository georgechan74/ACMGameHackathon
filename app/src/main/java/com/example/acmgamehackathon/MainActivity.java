package com.example.acmgamehackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.acmgamehackathon.menu.ScoreHistory;
import com.example.acmgamehackathon.menu.StartGame;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ImageView menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mDatabase = FirebaseDatabase.getInstance();
//        mReference = mDatabase.getReference();
//
//        mReference.setValue("test");

        setContentView(R.layout.activity_main);

    }

    public void scoreHistory(View view) {
        Intent intent = new Intent(this, ScoreHistory.class);
        this.startActivity(intent);    }

    public void startGame(View view) {
        Intent intent = new Intent(this, StartGame.class);
        this.startActivity(intent);    }
}
