package com.example.swimtraining;
import java.util.Calendar;
import java.util.Date;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
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
    private ResultsData res;
    private Date startTime = new Date();
    private Date endTime = new Date();


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
    }

    //Update textView with lapCounter
    private void display(int lapCounter) {
        TextView lapCounterTextView = (TextView) findViewById(R.id.textViewLapCounter);
        lapCounterTextView.setText("" + lapCounter);
    }

    //Function to go to ResultsActivity and send time + laps
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void callResultsActivity(View view) {
        //If timer is running -> stop timer and get current time
        if (running){
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
        }

        Bundle extras = getIntent().getExtras();
        Intent intent = new Intent(TimerActivity.this, ResultsActivity.class);

        //Insert the username profile
        String student = extras.getString("userName_Login");
        intent.putExtra("userName_Login", student);

        //Insert the type of filter
        Boolean typeClass = extras.getBoolean("type_alone");
        intent.putExtra("type_alone", typeClass);

        String nameClass = extras.getString("nameClass");
        intent.putExtra("nameClass", nameClass);


        //Change pauseOffset to string and add to intent that is sent to ResultsActivity
        String pauseOffsetString = String.valueOf(pauseOffset);
        intent.putExtra("keyPauseOffsetString", pauseOffsetString);

        String lapCounterString = String.valueOf(lapCounter);
        intent.putExtra("keyLapCounterString", lapCounterString);

        startActivity(intent);

    }

    //Function to start and stop the timer
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void startStopTime(View view) {
        if (!running) {
            startStopButton.setText(R.string.start_stop_button_Stop);
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
            startTime = Calendar.getInstance().getTime();
        }
        else if (running) {
            startStopButton.setText(R.string.start_stop_button_Start);
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
            endTime = Calendar.getInstance().getTime();
            Bundle extras = getIntent().getExtras();
            String userNameLogin = extras.getString("userName_Login");
            Boolean typeClass = extras.getBoolean("type_alone");
            String nameClass = extras.getString("nameClass");
            res = new ResultsData(userNameLogin, startTime, endTime, lapCounter, 50*lapCounter, typeClass, nameClass);
            GlobalVariables.resultsData.add(res);

            //Reset lap counter and timer
            pauseOffset = 0;
            lapCounter = 0;
            display(lapCounter);
            chronometer.setBase(SystemClock.elapsedRealtime());
        }
    }

}
