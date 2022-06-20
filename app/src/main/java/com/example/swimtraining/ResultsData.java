package com.example.swimtraining;
import java.util.Date;

public class ResultsData {
    Date startTime;
    Date endTime;
    long generalTime;
    int laps;
    int distance;
    double speed;

    public ResultsData(Date startTime, Date endTime,
                       int laps, int distance, double speed){
        this.startTime = startTime;
        this.endTime = endTime;
        this.generalTime = endTime.getTime() - startTime.getTime();
        this.laps = laps;
        this.distance = distance;
        this.speed = distance / generalTime;
    }
}
