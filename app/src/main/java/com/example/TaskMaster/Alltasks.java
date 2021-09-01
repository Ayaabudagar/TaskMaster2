package com.example.TaskMaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Alltasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alltasks);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}