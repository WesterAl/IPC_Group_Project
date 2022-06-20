package com.example.swimtraining;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
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
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

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
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        //Добавляем данные о пунктах списка:
        listDataHeader.add("Пункт 1");
        listDataHeader.add("Пункт 2");
        listDataHeader.add("Пункт 3");

        //Добавляем данные о подпунктах:
        List<String> top250 = new ArrayList<String>();
        top250.add("Подпункт 1.1");
        top250.add("Подпункт 1.2");
        top250.add("Подпункт 1.3");
        top250.add("Подпункт 1.4");
        top250.add("Подпункт 1.5");
        top250.add("Подпункт 1.6");
        top250.add("Подпункт 1.7");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("Подпункт 2.1");
        nowShowing.add("Подпункт 2.2");
        nowShowing.add("Подпункт 2.3");
        nowShowing.add("Подпункт 2.4");
        nowShowing.add("Подпункт 2.5");
        nowShowing.add("Подпункт 2.6");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("Подпункт 3.1");
        comingSoon.add("Подпункт 3.2");
        comingSoon.add("Подпункт 3.3");
        comingSoon.add("Подпункт 3.4");
        comingSoon.add("Подпункт 3.5");

        listDataChild.put(listDataHeader.get(0), top250);
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}