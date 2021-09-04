package com.example.TaskMaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
AppDataBase appDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
appDataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "tasks").allowMainThreadQueries()
        .build();
        List<Task> taskList = appDataBase.taskDao().getAll();
        // get the recycler view
        RecyclerView allTasksRecuclerView = findViewById(R.id.rs);
        // set a layout manager for this view
        allTasksRecuclerView.setLayoutManager(new LinearLayoutManager(this));
        // set the adapter for this recyclerView
        allTasksRecuclerView.setAdapter(new TaskAdapter(taskList));




        Button addTask = (Button) findViewById(R.id.button);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNewTask = new Intent(HomePage.this, Addtask.class);
                startActivity(addNewTask);
            }
        });
        Button allTaskss = (Button) findViewById(R.id.button2);
        allTaskss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAllTasks = new Intent(HomePage.this, Alltasks.class);
                startActivity(toAllTasks);
            }
        });
        Button setting =  findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSettingPage = new Intent(HomePage.this, Setting.class);
                startActivity(toSettingPage);
            }
        });
        Button labs401= (Button) findViewById(R.id.button4);
        labs401.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toLabsPage = new Intent(HomePage.this, Labs401.class);
                startActivity(toLabsPage);
            }
        });
        Button codeChallenges401= (Button) findViewById(R.id.button5);
        codeChallenges401.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toCodechallengesPage = new Intent(HomePage.this, CodeChallenges401.class);
                startActivity(toCodechallengesPage);
            }
        });
        Button sport= (Button) findViewById(R.id.button6);
        sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSportPagePage = new Intent(HomePage.this, Sport.class);
                startActivity(toSportPagePage);
            }
        });
        ArrayList<Task> allTasks = new ArrayList<Task>();
        allTasks.add(new Task("CodeChallenges401","Lorem, ipsum dolor sit amet consectetur adipisicing elit. Tempore debitis suscipit sint cum magni quibusdam veritatis explicabo temporibus officia adipisci vitae, voluptates dolore dignissimos fuga laborum laboriosam molestiae doloribus commodi.","new"));
        allTasks.add(new Task("Labs401"," Lorem, ipsum dolor sit amet consectetur adipisicing elit. Tempore debitis suscipit sint cum magni quibusdam veritatis explicabo temporibus officia adipisci vitae, voluptates dolore dignissimos fuga laborum laboriosam molestiae doloribus commodi. ","new"));
        allTasks.add(new Task("Sport","Lorem, ipsum dolor sit amet consectetur adipisicing elit. Tempore debitis suscipit sint cum magni quibusdam veritatis explicabo temporibus officia adipisci vitae, voluptates dolore dignissimos fuga laborum laboriosam molestiae doloribus commodi.","new"));

        RecyclerView allTask = findViewById(R.id.rs);
        allTask.setLayoutManager(new LinearLayoutManager(this));
        allTask.setAdapter(new TaskAdapter(allTasks));



    }
    @Override
    protected void onResume() {
        super.onResume();
        String welcomeMessage = "Welcome ";
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(HomePage.this);
        String instructorName = sharedPreferences.getString("instructorName", "Instructor");
        TextView instructorNameView = findViewById(R.id.textView14);
        instructorNameView.setText( instructorName + "s tasks");
    }
}
