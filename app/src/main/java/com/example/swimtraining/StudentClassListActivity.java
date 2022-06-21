package com.example.swimtraining;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentClassListActivity extends AppCompatActivity {
    // List view
    private ListView lv;

    // Listview Adapter
    ArrayAdapter<String> adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class_list);

        lv = (ListView) findViewById(R.id.list_view_student_class);

        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.list_item_for_student_class,
                R.id.list_item_student_class, GlobalVariables.classDates);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle extras = getIntent().getExtras();
                String student = extras.getString("userName_Login");
                TeacherListActivity.updateStudentsInClass(GlobalVariables.classDates.get(position).toString(), student);

                Intent i = new Intent(StudentClassListActivity.this, TimerActivity.class);
                i.putExtra("userName_Login", student);
                i.putExtra("nameClass", GlobalVariables.classDates.get(position).toString());
                startActivity(i);
            }
        });

    }
}