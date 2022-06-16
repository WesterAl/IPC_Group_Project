package com.example.swimtraining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Chronometer;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    //Initialize variable
    Chronometer chronometer;
    private long pauseOffset;
    private boolean running;

    private String str = "test string";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable
        chronometer = findViewById(R.id.chronometer);
        //chronometer.setFormat("Time: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());

    }
    //-----------------
    /** Called when the user taps the Send button */
    public void callResultsActivity(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("test", str);
        startActivity(intent);
        //Toast.makeText(this, "callConstrainToGuidelineActivity", Toast.LENGTH_SHORT).show();

    }
    //------------------

    //Function to start and stop the timer
    public void startStopTime(View view) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;

            //long time = chronometer.getBase();
            //Toast.makeText(this, (int) time, Toast.LENGTH_SHORT).show();
        }
        else if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

}
