package com.example.TaskMaster;

import android.support.v7.app.AppCompatActivity;
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

public class Addtask extends AppCompatActivity {
AppDataBase appDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final EditText title = findViewById(R.id.taskTitle);
        final EditText body = findViewById(R.id.descreption);
        final EditText state = findViewById(R.id.taskstate);
        Button addTask = findViewById(R.id.button3);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Task task = new Task( title.getText().toString(),body.getText().toString(),state.getText().toString());
                appDataBase = Room.databaseBuilder(getApplicationContext(),AppDataBase.class,"tasks").allowMainThreadQueries().build();
                TaskDao taskDao = appDataBase.taskDao();
                appDataBase.taskDao().insertAll(task);
                Toast.makeText(getApplicationContext(), "Submitted!", Toast.LENGTH_LONG).show();
            }
        });
        Todo todo = Todo.builder()
                .title(title.getText().toString())
                .body(body.getText().toString())
                .state(state.getText().toString())
                .build();

        Amplify.API.mutate(
                ModelMutation.create(todo),
                response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                error -> Log.e("MyAmplifyApp", "Create failed", error)
        );

    }

}