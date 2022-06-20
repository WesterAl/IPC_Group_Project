package com.example.swimtraining;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Date;

public class ResultsData {
    String name;
    Date startTime;
    Date endTime;
    long generalTime;
    int laps;
    int distance;
    double speed;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ResultsData(String name, Date startTime, Date endTime,
                       int laps, int distance){
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.generalTime = endTime.getTime() - startTime.getTime();
        this.laps = laps;
        this.distance = distance;
        this.speed = distance / generalTime;



    }
}
