package com.example.swimtraining;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class ResultsActivity extends AppCompatActivity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    static List<String> listDataHeader = new ArrayList<String>();
    static HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //Связываемся с нашим ExpandableListView:
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        //Подготавливаем список данных:
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        //Настраиваем listAdapter:
        expListView.setAdapter(listAdapter);
    }

    /*
     * Подготавливаем данные для списка:
     */

    private List<String> prepareData(int i){
        List<String> list = new ArrayList<String>();
        list.add("Name: " + GlobalVariables.resultsData.get(i).name);
        list.add("Time: " + GlobalVariables.resultsData.get(i).generalTime + " sec");
        list.add("Laps: " + GlobalVariables.resultsData.get(i).laps);
        list.add("Distance: " + GlobalVariables.resultsData.get(i).distance + " m");
        list.add("Speed: " + GlobalVariables.resultsData.get(i).speed + " m/s");
        return list;

    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        for (int i = 0; i < GlobalVariables.resultsData.size(); i++){
            String head = sdf.format(GlobalVariables.resultsData.get(i).startTime) + " - " + sdf.format(GlobalVariables.resultsData.get(i).endTime);
            listDataHeader.add(head);
            listDataChild.put(listDataHeader.get(i), prepareData(i));
        }
    }
}