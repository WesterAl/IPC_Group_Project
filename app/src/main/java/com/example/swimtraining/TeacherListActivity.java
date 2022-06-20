package com.example.swimtraining;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import android.view.View;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TeacherListActivity extends AppCompatActivity {

    public static class Swimmer {
        public String name;
        public double time;
    }

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    static List<String> listDataHeader = new ArrayList<String>();
    static HashMap<String, List<Swimmer>> listDataChild = new HashMap<String, List<Swimmer>>();
    FloatingActionButton btnAddClass;
    Button btnDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        //add class
        btnAddClass = (FloatingActionButton) findViewById(R.id.buttonAddClass);
        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TeacherListActivity.this, TeacherNewClassActivity.class));

            }
        });

        //TeacherListActivity.prepareListData();


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        // preparing list data
        //prepareListData();

        listAdapter = new com.example.swimtraining.ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

    }



    /*
     * Preparing the list data
     */
    public static void updateClass(String date){
        listDataHeader.add(date);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void updateStudentsInClass(String date, String student, double time){
        //int indexClass = listDataHeader.indexOf(date);

        Swimmer s = new Swimmer();
        s.name = student;
        s.time = time;


        List<Swimmer> students = listDataChild.get(date);
        if(students == null){
            students = new ArrayList<Swimmer>();
            students.add(s);
            listDataChild.put(date, students);
        }
        else {
            students.add(s);
            listDataChild.replace(date, students); //insert updated list
        }


    }

    public static void prepareListData() {

        // Adding child data
        listDataHeader.add("2022/06/17");
        listDataHeader.add("2022/06/18");
        listDataHeader.add("2022/06/19");

        // Adding child data
        List<Swimmer> top250 = new ArrayList<Swimmer>();
        Swimmer s = new Swimmer();
        s.name = "student";
        s.time = 6.2;
        top250.add(s);
        top250.add(s);
        top250.add(s);

        List<Swimmer> nowShowing = new ArrayList<Swimmer>();
        nowShowing.add(s);
        nowShowing.add(s);
        nowShowing.add(s);
        nowShowing.add(s);

        List<Swimmer> comingSoon = new ArrayList<Swimmer>();
        comingSoon.add(s);
        comingSoon.add(s);

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }
}