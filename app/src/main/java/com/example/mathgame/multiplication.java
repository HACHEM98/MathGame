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

public class multiplication extends AppCompatActivity {
TextView t1;
TextView t2;
TextView t3;
TextView t4;
EditText et;
Button b17;
Button b2;
int num1;
int num2;
Random r =new Random();
int userAnswer;
int realAnswer;
int userScore=0;
int userLife=3;

CountDownTimer timer;
public static final long START_TIME_IN_MILIS=30000;
boolean timmerunning;
Long time_left_in_milis=START_TIME_IN_MILIS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);
        t1=findViewById(R.id.sc_l);
        t2=findViewById(R.id.l_l);
        t3=findViewById(R.id.t_t);
        t4=findViewById(R.id.q);
        et=findViewById(R.id.h);
        b17=findViewById(R.id.b111111);
        b2=findViewById(R.id.b22);
        gameContinue();
        startTime();
  b17.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          pauseTimmer();
          userAnswer=Integer.valueOf(et.getText().toString());
          if(userAnswer==realAnswer){
              t4.setText("Congratulations . Your Answer is True");
              userScore+=10;
              t1.setText(String.valueOf(userScore));
          }
          else
          {
              t4.setText("Sorry . Your Answer is Wrong");
              userLife=userLife-1;
              t2.setText(String.valueOf(userLife));
              checkLife();
          }
      }
  });

  b2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          et.setText("");
          gameContinue();
          restTimmer();
          startTime();
      }
  });



    }

    public void checkLife(){
        if(userLife<=0)
        {
            Toast.makeText(getApplicationContext(),"Game is Over",Toast.LENGTH_SHORT).show();
            Intent intent =new Intent(multiplication.this,result.class);
            intent.putExtra("scor",userScore);
            startActivity(intent);
            finish();
        }

    }


    public void gameContinue(){
        num1=r.nextInt(100);
        num2=r.nextInt(100);
        realAnswer=num1*num2;
        t4.setText(num1+"*"+num2);
        b17.setClickable(true);
    }


    public void startTime(){
         timer =new CountDownTimer(time_left_in_milis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_milis=millisUntilFinished;
                updateText();
            }

            @Override
            public void onFinish() {
             timmerunning=false;
             pauseTimmer();
             restTimmer();
             updateText();
             t4.setText("Time is Over");
             userLife--;
             checkLife();
            }
        }.start();
        timmerunning=true;


    }

    public void updateText(){
        int seconds=(int)((time_left_in_milis/1000)%60);
        String time_left=String.format(Locale.getDefault(),"%2d",seconds);
        t3.setText(time_left);
    }

    public void pauseTimmer(){
        if(timmerunning)
        {
            timer.cancel();
            timmerunning=false;
        }
    }

    public void restTimmer(){
        time_left_in_milis=START_TIME_IN_MILIS;
        updateText();

    }





}