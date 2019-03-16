package com.example.acmgamehackathon.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.acmgamehackathon.R;
import com.example.acmgamehackathon.fragments.CharacterSelect;

public class StartGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CharacterSelect characterSelect = new CharacterSelect();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, characterSelect);
        fragmentTransaction.commit();

        setContentView(R.layout.activity_game);



    }
}
