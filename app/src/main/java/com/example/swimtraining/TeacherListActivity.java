package com.example.swimtraining;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
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

    static ExpandableListAdapter listAdapter;
    static ExpandableListView expListView;
    static List<String> listDataHeader = new ArrayList<String>();
    static HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();
    FloatingActionButton btnAddClass;



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

        Context atualContext = getApplicationContext();
        expandableList(R.id.listTeacher, GlobalVariables.classDates, listDataChild, listAdapter, expListView, atualContext);

        //Return button/Arrow
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    public void expandableList(int id, List<String> ListDataHeaderParam, HashMap<String, List<String>> ListDataChildParam, ExpandableListAdapter listAdapterParam, ExpandableListView expListViewParam, Context context){
        expListViewParam = (ExpandableListView) findViewById(id);
        listAdapterParam = new com.example.swimtraining.ExpandableListAdapter(context, ListDataHeaderParam, ListDataChildParam);

        // setting list adapter
        expListViewParam.setAdapter(listAdapterParam);

        // Listview Group click listener
        expListViewParam.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });

        // Listview on child click listener
        expListViewParam.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Toast.makeText(
                        context,
                        ListDataHeaderParam.get(groupPosition)
                                + " : "
                                + ListDataChildParam.get(
                                ListDataHeaderParam.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();

                Intent intent = new Intent(TeacherListActivity.this, ResultsActivity.class);

                intent.putExtra("type_alone", false);
                intent.putExtra("userName_Login",  ListDataChildParam.get(
                        ListDataHeaderParam.get(groupPosition)).get(
                        childPosition));
                intent.putExtra("nameClass", ListDataHeaderParam.get(groupPosition));
                startActivity(intent);
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
        List<String> students = listDataChild.get(date);
        if(students == null){
            students = new ArrayList<String>();

            if(!students.contains(student)){
                students.add(student);
                listDataChild.put(date, students);
            }
        }
        else {
            students.add(student);
            listDataChild.replace(date, students);
        }
    }

    //Return button/Arrow
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}