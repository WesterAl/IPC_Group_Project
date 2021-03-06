package com.example.swimtraining;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class TeacherNewClassActivity extends AppCompatActivity {

    String chosenDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_new_class);

        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView);
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                chosenDate = year  + "/" + month + "/" + dayOfMonth;
            }
        });

        Button btnConfirmedAddClass = (Button)findViewById(R.id.button_date);
        btnConfirmedAddClass.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                GlobalVariables.classDates.add(chosenDate);
                startActivity(new Intent(TeacherNewClassActivity.this, TeacherListActivity.class));
                Toast.makeText(getApplicationContext(), chosenDate, Toast.LENGTH_LONG).show();
                TeacherListActivity.updateClass(chosenDate);
            }
        });

                //TeacherListActivity.prepareListData();

        //Return button/Arrow
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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