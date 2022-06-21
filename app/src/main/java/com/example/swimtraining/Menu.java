package com.example.swimtraining;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);



        Bundle extras = getIntent().getExtras();
        String userName = extras.getString("userName_Login");

        Button btn = (Button) findViewById(R.id.alone_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this, TimerActivity.class);
                intent.putExtra("userName_Login", userName);
                startActivity(intent);
            }
        });
    }


}

