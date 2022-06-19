package com.example.swimtraining;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class activity_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Spinner spinnerProfile = findViewById(R.id.spinner_profile_login);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.profile, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerProfile.setAdapter(adapter);


        Button btn = (Button)findViewById(R.id.login_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, MyOtherActivity.class));

                String selectedProfile = spinnerProfile.getSelectedItem ().toString();

                if(selectedProfile.equals("Student")){

                    Toast.makeText(activity_login.this, selectedProfile, Toast.LENGTH_LONG).show();
                    //TODO: change the startActivity to your pageActivity if student
                    //startActivity(new Intent(activity_login.this, TeacherListActivity.class));
                }
                else if (selectedProfile.equals("Teacher")){

                    Intent intent = new Intent(activity_login.this, TeacherListActivity.class);
                    EditText editTextNameProfileLogin = (EditText) findViewById((R.id.name_profile_login));
                    String nameLogin = editTextNameProfileLogin.getText().toString();

                    intent.putExtra("name_login", nameLogin);
                    startActivity(intent);
                }

            }
        });
    }

}