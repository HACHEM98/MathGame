package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {
TextView score;
Button play;
Button exit;
int scor=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        score=findViewById(R.id.s);
        play=findViewById(R.id.pa);
        exit=findViewById(R.id.e);
        Intent intent=getIntent();
        scor=intent.getIntExtra("scor",0);
        String UserScor=String.valueOf(scor);

        score.setText("Your Score is :"+UserScor);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(result.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }
}