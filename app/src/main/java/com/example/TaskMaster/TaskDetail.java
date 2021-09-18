package com.example.TaskMaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;

import java.io.File;

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

        //download img from storage
        Amplify.Storage.downloadFile(
                //get img from intent
                intent.getExtras().getString("img"),
                new File(getApplicationContext().getFilesDir() + "/download.jpg"),
                result -> {
                    //  target imageView
                    ImageView imgTask = findViewById(R.id.imgTask);
                    String newImg = result.getFile().getPath();
                    imgTask.setImageBitmap(BitmapFactory.decodeFile(newImg));

                    Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile());},
                error -> Log.e("MyAmplifyApp",  "Download Failure", error)
        );

    }

}