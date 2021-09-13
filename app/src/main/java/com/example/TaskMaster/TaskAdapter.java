package com.example.TaskMaster;



import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Todo;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    List<Todo> allTasks = new ArrayList<Todo>();

    public TaskAdapter(List<Todo> allTasks)  {
        this.allTasks = allTasks;
    }
    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        public Todo todo;
        View taskView;
        public TaskViewHolder(@NonNull View taskView) {
            super(taskView);
            this.taskView = taskView;
            itemView.findViewById(R.id.taskTitleInFragment).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent toTaskDetailsPage = new Intent(v.getContext() ,TaskDetail.class);
                    toTaskDetailsPage.putExtra("title",todo.getTitle());
                    toTaskDetailsPage.putExtra("body",todo.getBody());
                    toTaskDetailsPage.putExtra("state",todo.getState());
                    v.getContext().startActivity(toTaskDetailsPage);
                }
            });
        }
    }




    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 7- create the view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.todo = allTasks.get(position);

//        TextView title = holder.itemView.findViewById(R.id.taskTitleInFragment);
//
//        title.setText(holder.todo.getTitle());
        Button titleButton = holder.itemView.findViewById(R.id.taskTitleInFragment);
        titleButton.setText(holder.todo.getTitle());



    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }

}
