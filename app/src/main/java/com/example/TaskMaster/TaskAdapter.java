package com.example.TaskMaster;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        TextView title = holder.itemView.findViewById(R.id.taskTitleInFragment);
        TextView body = holder.itemView.findViewById(R.id.taskBodyInFragment);

        title.setText(holder.todo.getTitle());
        body.setText(holder.todo.getBody());

    }

    @Override
    public int getItemCount() {
        return allTasks.size();
    }

}
