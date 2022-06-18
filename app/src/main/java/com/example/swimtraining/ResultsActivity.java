package com.example.swimtraining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    TextView receiver_msg;
    long pauseOffset = 0;
    String pauseOffsetString;
    String lapCounterString;
    int lapCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        receiver_msg = findViewById(R.id.textViewTest);

        // create the get Intent object
        Intent intent = getIntent();

        // receive the value by getStringExtra() method
        // and key must be same which is send by first activity
        pauseOffsetString = intent.getStringExtra("keyPauseOffsetString");
        pauseOffset = Long.parseLong(pauseOffsetString);
        System.out.println("Från ResultsActivity pauseOffset " + pauseOffset);
        // display the string into textView
        receiver_msg.setText(pauseOffsetString + " in milliseconds");

        lapCounterString = intent.getStringExtra("keyLapCounterString");
        lapCounter = Integer.parseInt(lapCounterString);
        System.out.println("Från ResultsActivity lapCounter " + lapCounter);






    }



}