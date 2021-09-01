package com.example.TaskMaster;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button submitInstructorInfo = findViewById(R.id.button7);

        submitInstructorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Setting.this);
                SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

                EditText instructorNameField = Setting.this.findViewById(R.id.editTextTextPersonName);
                String instructorName = instructorNameField.getText().toString();

                sharedPreferencesEditor.putString("instructorName", instructorName);
                sharedPreferencesEditor.apply();

            }
        });
    }
}