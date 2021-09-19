package com.example.TaskMaster;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.room.Room;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Todo;


import java.io.FileNotFoundException;
import java.io.InputStream;

public class Addtask extends AppCompatActivity {
AppDataBase appDataBase;
String bodyFromAnotherApp ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent recive = getIntent();
        if(recive.getType() != null && recive.getType().equals("text/plain")){
            Log.i("Hello world" , recive.getExtras().get(Intent.EXTRA_TEXT).toString());
            bodyFromAnotherApp=recive.getExtras().get(Intent.EXTRA_TEXT).toString();
        }
        final EditText title = findViewById(R.id.taskTitle);
        final EditText body = findViewById(R.id.descreption);
        final EditText state = findViewById(R.id.taskstate);
        Button addTask = findViewById(R.id.button3);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bodyText;
if(bodyFromAnotherApp != null ){
    bodyText = bodyFromAnotherApp;
}else {
    bodyText=body.getText().toString();
}
                Todo todo = Todo.builder()
                        .title(title.getText().toString())
                        .body(bodyText)
                        .state(state.getText().toString())
                        .build();

                Amplify.API.mutate(
                        ModelMutation.create(todo),
                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                );

                Toast.makeText(getApplicationContext(), "Submitted!", Toast.LENGTH_LONG).show();
            }
        });

        Button uploadImg = findViewById(R.id.uploadImg);
        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFile();
            }
        });

    }
    // method to pick file from mobile system
    private void pickFile(){
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");  //any type of file
        chooseFile = Intent.createChooser(chooseFile, "Choose a file");
//        startActivity(chooseFile);
        startActivityForResult(chooseFile, 1234);
    }

    // upload img from input stream to amplify

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(data+"==============================================");
        try {
            //getdata from intent and set it inside inputStream
            if(data !=null) {
            InputStream imgInputStream = getContentResolver().openInputStream(data.getData());
            //add data to amplify
            Amplify.Storage.uploadInputStream(
                    "taskImg",
                    imgInputStream,
                    result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            );
            }
        } catch (FileNotFoundException error) {
            Log.e("MyAmplifyApp", "Could not find file to open for input stream.", error);
        }


    }

}