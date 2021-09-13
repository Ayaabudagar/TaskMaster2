package com.example.TaskMaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        Button homePage = findViewById(R.id.tohomepage);
        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHomePage = new Intent(TaskDetail.this , HomePage.class);
                startActivity(goToHomePage);
            }
        });
        Intent intent = getIntent();

        TextView titleText = findViewById(R.id.textViewtitle2);
        TextView stateText = findViewById(R.id.textViewstate2);
        TextView BodyText = findViewById(R.id.textViewBody);

        String title = intent.getStringExtra("title");
        String body = intent.getStringExtra("body");
        String state= intent.getStringExtra("state");


        titleText.setText(title);
        stateText.setText(state);
        BodyText.setText(body);

    }

}