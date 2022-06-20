package com.example.swimtraining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    //Initialize variable
    Chronometer chronometer;
    //pauseOffset has the current time of the chronometer
    private long pauseOffset;
    private boolean running;

    private int lapCounter = 0;

    Button startStopButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        //Assign variable
        chronometer = findViewById(R.id.chronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());

        //textView for lap counter
        TextView lapCounterTextView = (TextView) findViewById(R.id.textViewLapCounter);
        lapCounterTextView.setText("0");

        startStopButton = findViewById(R.id.startStopButton);
        startStopButton.setText(R.string.start_stop_button_Start);


    }

    //Function to add laps
    public void addLap(View view){
        lapCounter = lapCounter + 1;
        display(lapCounter);
        //System.out.println(lapCounter);0
    }

    //Update textView with lapCounter
    private void display(int lapCounter) {
        TextView lapCounterTextView = (TextView) findViewById(R.id.textViewLapCounter);
        //Application stops if -> setText(lapCounter)
        lapCounterTextView.setText("" + lapCounter);
    }

    //Function to go to ResultsActivity and send time + laps
    public void callResultsActivity(View view) {
        //If timer is running -> stop timer and get current time
        if (running){
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
        }

        // Do something in response to button
        Intent intent = new Intent(this, ResultsActivity.class);

       //Change pauseOffset to string and add to intent that is sent to ResultsActivity
        String pauseOffsetString = String.valueOf(pauseOffset);

        intent.putExtra("keyPauseOffsetString", pauseOffsetString);

        String lapCounterString = String.valueOf(lapCounter);
        intent.putExtra("keyLapCounterString", lapCounterString);
        startActivity(intent);

        //Reset lap counter and timer
        pauseOffset = 0;
        lapCounter = 0;
        display(lapCounter);
        chronometer.setBase(SystemClock.elapsedRealtime());
    }

    //Function to start and stop the timer
    public void startStopTime(View view) {
        if (!running) {
            startStopButton.setText(R.string.start_stop_button_Stop);
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;

        }
        else if (running) {
            startStopButton.setText(R.string.start_stop_button_Start);
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

}
