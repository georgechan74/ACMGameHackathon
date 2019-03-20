package com.example.acmgamehackathon;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.acmgamehackathon.models.Game;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ScoreHistory extends AppCompatActivity {
    private static final String TAG = "ScoreHistory";
    private RecyclerView mRecyclerView;
    private ScoreHistoryAdapter mAdapter;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ArrayList<Game> games = new ArrayList<Game>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_history);
        mRecyclerView = (RecyclerView) findViewById(R.id.score_history_rv);
        mAdapter = new ScoreHistoryAdapter(this, games);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Log.d(TAG, "testing here!");

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("Matches");

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Game> matches = new ArrayList<>();
                resetRecyclerVew();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    Log.d(TAG, "node = " + ds.getValue());
//                    Log.d(TAG, "ID = " + (long)ds.child("ID").getValue());
//                    Log.d(TAG, "Winner = " + (String)ds.child("Winner").getValue());
//                    Log.d(TAG, "TeamA = " + (String)ds.child("TeamA").getValue());
//                    Log.d(TAG, "TeamB = " + (String)ds.child("TeamB").getValue());
//                    Log.d(TAG, "Score = " + (long)ds.child("Score").getValue());

                    Game theGame = new Game((long)ds.child("ID").getValue(),
                            (String)ds.child("Winner").getValue(),
                            (String)ds.child("TeamA").getValue(),
                            (String)ds.child("TeamB").getValue(),
                            (long)ds.child("Score").getValue());
                    matches.add(theGame);
                }

                games = matches;

                populateRecyclerView(games);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void populateRecyclerView(List<Game> games){
        mAdapter.mGames.addAll(games);
        mAdapter.notifyDataSetChanged();
    }

    public void resetRecyclerVew() {
        mAdapter.mGames.clear();
        mAdapter.notifyDataSetChanged();
    }
}
