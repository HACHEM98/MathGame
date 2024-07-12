package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class game_soustratction extends AppCompatActivity {
TextView t1;
TextView t2;
TextView t3;
TextView t4;
TextView t5;
TextView t6;
EditText e;
TextView t7;
Button b1;
Button b2;
Random r =new Random();
int number1;
int number2;
int userAnswer;
int realAnswer;
int userScore;
int userLife=3;
CountDownTimer t;
public static final long START_TIME_IN_MILIS=30000;
boolean timerunnig;
long time_left_in_milis=START_TIME_IN_MILIS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_soustratction);
        t1=findViewById(R.id.s);
        t2=findViewById(R.id.s_l1);
        t3=findViewById(R.id.life);
        t4=findViewById(R.id.life_l3);
        t5=findViewById(R.id.te);
        t6=findViewById(R.id.t_l);
        t7=findViewById(R.id.question);
        e=findViewById(R.id.et);
        b1=findViewById(R.id.ok);
        b2=findViewById(R.id.nq);
gameContinue();
startTime();
b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        pauseTimmer();
 userAnswer=Integer.valueOf(e.getText().toString());
 if(userAnswer==realAnswer)
 {
     t7.setText("Congratulations.Your Answer is True");
     userScore+=10;
     t2.setText(String.valueOf(userScore));
 }
 else
 {
     userLife=userLife-1;
     t7.setText("Sorry.Your Answer is Wrong");
     t4.setText(String.valueOf(userLife));
     checkLife();
 }
    }
});



b2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        e.setText("");
        gameContinue();
        resetTimmer();
        startTime();

    }
});
    }
    public void gameContinue(){
         number1=r.nextInt(100);
         number2=r.nextInt(number1);
        t7.setText(number1+"-"+number2);
        realAnswer=number1-number2;
        b1.setClickable(true);
    }
    public void checkLife() {
        if (userLife <= 0) {
            Toast.makeText(getApplicationContext(), "Game is Over", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(game_soustratction.this, result.class);
            intent.putExtra("scor", userScore);
            startActivity(intent);
            finish();
        }


    }

    public void startTime(){
         t=new CountDownTimer(time_left_in_milis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
             time_left_in_milis=   millisUntilFinished;
             updateText();
            }

            @Override
            public void onFinish() {
             timerunnig=false;
             pauseTimmer();
             resetTimmer();
             updateText();
             userLife--;
             t7.setText("Time is over");
             checkLife();
            }
        }.start();
          timerunnig=true;



    }

    public void updateText(){
        int seconds = (int) ((time_left_in_milis/1000)%60);
        String time_left=String.format(Locale.getDefault(),"%2d",seconds);
        t6.setText(time_left);
    }

     public void pauseTimmer(){
        if(timerunnig)
        {
            t.cancel();
            timerunnig=false;
        }
     }

     public void resetTimmer(){
        time_left_in_milis=START_TIME_IN_MILIS;
        updateText();
     }



}