package com.example.swimtraining;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TeacherListActivity extends AppCompatActivity {

    public static class Swimmer {
        public String name;
        public double time;
    }

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    static List<String> listDataHeader = new ArrayList<String>();
    static HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();
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

        expandableList(R.id.lvExp, listDataHeader, listDataChild, listAdapter, expListView);
    }


    public void expandableList(int id, List<String> ListDataHeaderParam, HashMap<String, List<String>> ListDataChildParam, ExpandableListAdapter listAdapterParam, ExpandableListView expListViewParam){
        // get the listview
        expListViewParam = (ExpandableListView) findViewById(id);
        // preparing list data
        //prepareListData();

        listAdapterParam = new com.example.swimtraining.ExpandableListAdapter(this, ListDataHeaderParam, ListDataChildParam);

        // setting list adapter
        expListViewParam.setAdapter(listAdapterParam);

        // Listview Group click listener
        expListViewParam.setOnGroupClickListener(new OnGroupClickListener() {

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
        expListViewParam.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        ListDataHeaderParam.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListViewParam.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        ListDataHeaderParam.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListViewParam.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        ListDataHeaderParam.get(groupPosition)
                                + " : "
                                + ListDataChildParam.get(
                                ListDataHeaderParam.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }


    /*
     * Methods to update Class and update Students into class
     */
    public static void updateClass(String date){
        listDataHeader.add(date);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void updateStudentsInClass(String date, String student){

        String s = new String(student);

        List<String> students = listDataChild.get(date);
        if(students == null){
            students = new ArrayList<String>();
            students.add(s);
            listDataChild.put(date, students);
        }
        else {
            students.add(s);
            listDataChild.replace(date, students);
        }
    }

}