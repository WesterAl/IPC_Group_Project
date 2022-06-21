package com.example.swimtraining;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Date;

public class ResultsData {
    String name;
    Date startTime;
    Date endTime;
    float generalTime;
    int laps;
    int distance;
    double speed;
    boolean alone = true;
    String nameClass;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ResultsData(String name, Date startTime, Date endTime,
                       int laps, int distance, boolean alone, String nameClass){
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.generalTime = (endTime.getTime() - startTime.getTime())/1000;
        this.laps = laps;
        this.distance = distance;
        this.speed = distance / generalTime;
        this.alone = alone;
        this.nameClass = nameClass;



    }
}
