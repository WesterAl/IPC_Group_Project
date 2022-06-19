package com.example.swimtraining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class ResultsActivity extends AppCompatActivity {
    //PROBLEMS
    //Variables do not have decimals

    TextView timeTextview;
    TextView lapsTextView;
    TextView distanceTextView;
    TextView averageSpeedTextView;

    long pauseOffset = 0; //Time from timer
    float timeInSec = 0;
    String pauseOffsetString;
    String lapCounterString;

    long lapCounter;    //Laps
    long distance = 0;
    long averageSpeed = 0;
    long poolLength = 50; //Pool length in m

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        try {

            //Find TextView elements
            timeTextview = findViewById(R.id.timeTextView);
            lapsTextView = findViewById(R.id.lapsTextView);
            distanceTextView = findViewById(R.id.distanceTextView);
            averageSpeedTextView = findViewById(R.id.averageSpeedTextView);

            // create the get Intent object
            Intent intent = getIntent();

            // receive the value by getStringExtra() method
            // and key must be same which is sent by first activity
            pauseOffsetString = intent.getStringExtra("keyPauseOffsetString");
            pauseOffset = Long.parseLong(pauseOffsetString);
            //System.out.println(pauseOffset);
            timeInSec = pauseOffset / 1000;
            timeTextview.setText("Time: " + timeInSec + " sec");


            lapCounterString = intent.getStringExtra("keyLapCounterString");
            lapCounter = Integer.parseInt(lapCounterString);
            lapsTextView.setText("Laps: " + lapCounterString);
            //System.out.println("Från ResultsActivity lapCounter " + lapCounter);
            //System.out.println(pauseOffset);
            //System.out.println(timeInSec);


            //Calculate distance and average speed
            if (!(lapCounter == 0) || !(pauseOffset == 0)){
                distance = lapCounter * poolLength;
                averageSpeed = (distance / (pauseOffset / 1000));
                distanceTextView.setText("Distance: " + String.valueOf(distance) + " m");
                averageSpeedTextView.setText("Speed: " + String.valueOf(averageSpeed) + " m/s");

            }
            else {
                distanceTextView.setText("Distance: 0 m");
                averageSpeedTextView.setText("Speed: 0 m/s");
            }


        } catch (Exception e) {
            System.out.println("Error " + e);
        }









    }



}