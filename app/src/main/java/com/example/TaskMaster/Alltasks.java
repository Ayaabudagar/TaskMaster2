package com.example.TaskMaster;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Alltasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alltasks);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}