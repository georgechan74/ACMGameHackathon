package com.example.acmgamehackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private HashMap<String, String> mValues;
    private RecyclerView mRecyclerView;
    private TextView mTV;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.go_to_rv);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScoreHistory.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        Log.d(TAG, "testing");
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("Matches");
        mTV = (TextView) findViewById(R.id.testText);
        if (mTV == null) {
            Log.d(TAG, "is null");
        }
        mTV.setText("testing2");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildren() == null) {
                    Log.d(TAG, "nothing!");
                }
                else {
                    Log.d(TAG, "something!");
                    int i = 0;

                    for (DataSnapshot s : dataSnapshot.getChildren()) {
                        i++;
                        Log.d(TAG, "" + s.getValue().getClass().getName());
                        mTV.setText(s.getKey());
                    }

                    Log.d(TAG, "done " + i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Log.d(TAG, "finished");
    }
}
