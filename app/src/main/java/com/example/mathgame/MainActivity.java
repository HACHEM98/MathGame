package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button add;
Button sous;
Button mul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=findViewById(R.id.button1);
        sous=findViewById(R.id.button2);
        mul=findViewById(R.id.button3);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,game_addition.class);
                startActivity(intent);
                finish();
            }
        });
        sous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,game_soustratction.class);
                startActivity(intent);
                finish();
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,multiplication.class);
                startActivity(intent);
                finish();
            }
        });
    }
}