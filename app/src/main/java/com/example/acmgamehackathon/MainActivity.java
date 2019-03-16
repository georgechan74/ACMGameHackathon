package com.example.acmgamehackathon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
        menuItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int id = view.getId();
                Toast.makeText(getApplicationContext(), id + " ", Toast.LENGTH_LONG).show();
            }

        });

    }
}
