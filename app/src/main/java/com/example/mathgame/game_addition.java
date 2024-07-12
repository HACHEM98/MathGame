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

import com.example.mathgame.R;

import java.util.Locale;
import java.util.Random;

public class game_addition extends AppCompatActivity {
    TextView score;
    TextView life;
    TextView t_l;
    TextView question;
    EditText answer;
    Button ok;
    Button nextquestion;
    Random random = new Random();
    int number1;
    int number2;
    int realAnswer;
    int userAnswer;
    int userScore = 0;
    int userLife = 3;
    CountDownTimer timeee;
    private static final long START_TIMER_IN_MILIS = 30000;
    boolean timerunnig;
    long time_left_in_milis = START_TIMER_IN_MILIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_addition);
        score = findViewById(R.id.score_l);
        life = findViewById(R.id.life_l);
        t_l = findViewById(R.id.t_l);
        question = findViewById(R.id.tv);
        answer = findViewById(R.id.et);
        ok = findViewById(R.id.btn1);
        nextquestion = findViewById(R.id.btn2);
        gamecontinue();
        startTime();
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseTimer();
                userAnswer = Integer.valueOf(answer.getText().toString());

                if (userAnswer == realAnswer) {
                    userScore += 10;
                    question.setText("Congratulations. Your Answer is True");
                    score.setText(String.valueOf(userScore));
                } else {
                    userLife -= 1;
                    question.setText("Sorry. Your Answer is Wrong");
                    life.setText(String.valueOf(userLife));
                    checkLife();
                }
            }
        });

        nextquestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answer.setText("");
                gamecontinue();
                restTimer();
                startTime();

            }
        });
    }

    private void checkLife() {
        if (userLife <= 0) {
            Toast.makeText(getApplicationContext(), "Game is Over", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(game_addition.this, result.class);
            intent.putExtra("scor", userScore);
            startActivity(intent);
            finish();
        }
    }

    public void gamecontinue() {
        number1 = random.nextInt(100);
        number2 = random.nextInt(100);
        question.setText(number1 + "+" + number2);
        realAnswer = number1 + number2;
        ok.setClickable(true);
    }

    public void startTime() {
        timeee = new CountDownTimer(time_left_in_milis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_milis = millisUntilFinished;
                updateText();
            }

            @Override
            public void onFinish() {
                timerunnig = false;
                pauseTimer();
                restTimer();
                updateText();
                userLife--;
                question.setText("Time is Over");
                checkLife();
            }
        }.start();
        timerunnig = true;
    }

    public void updateText() {
        int seconds = (int) (time_left_in_milis / 1000) % 60;
        String time_left = String.format(Locale.getDefault(), "%2d", seconds);
        t_l.setText(time_left);
    }

    public void pauseTimer() {
        if (timerunnig) {
            timeee.cancel();
            timerunnig = false;
        }
    }

    public void restTimer() {
        time_left_in_milis = START_TIMER_IN_MILIS;
        updateText();
    }
}
