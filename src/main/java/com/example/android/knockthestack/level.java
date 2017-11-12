package com.example.android.knockthestack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class level extends AppCompatActivity {

    private String name="";
    MainActivity leveldecide = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        final Button level = (Button)findViewById(R.id.play);
        level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(level.this,MainActivity.class);
                startActivity(intent);
            }
        });




        final Button easy = (Button)findViewById(R.id.easy);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String buttonTextEasy = "Easy";
                leveldecide.startgame(buttonTextEasy);
                Intent intent = new Intent(level.this,MainActivity.class);
                startActivity(intent);
            }
        });


        final Button medium = (Button)findViewById(R.id.medium);
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String buttonTextmedium = "Medium";
                leveldecide.startgame(buttonTextmedium);
                Intent intent = new Intent(level.this,MainActivity.class);
                startActivity(intent);
            }
        });

        final Button hard = (Button)findViewById(R.id.hard);
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String buttonTexthard = "Hard";
                leveldecide.startgame(buttonTexthard);
                Intent intent = new Intent(level.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }


    }

