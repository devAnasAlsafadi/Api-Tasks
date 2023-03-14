package com.ti.apitraining.Adapter.task;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ti.apitraining.databinding.ItemTaskBinding;
import com.ti.apitraining.interfaces.TaskCallback;
import com.ti.apitraining.models.Task;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksHolder> {

    List<Task> list ;
    TaskCallback listeners;

    public TasksAdapter(List<Task> list,TaskCallback listeners) {
        this.list = list;
        this.listeners = listeners;
    }


    @NonNull
    @Override
    public TasksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTaskBinding binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new TasksHolder(binding,listeners);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksHolder holder, int position) {
        holder.setData(list.get(position));
        int id = list.get(position).id;
        int pos = position;

        holder.binding.imEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listeners.onClickUpdateTasks(id,pos);
            }
        });

        holder.binding.imDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listeners.onClickDeleteTasks(id,pos);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public List<Task> getList() {
        return list;
    }
    public void setList(List<Task> list) {
        this.list = list;
    }
    public TaskCallback getListeners() {
        return listeners;
    }
    public void setListeners(TaskCallback listeners) {
        this.listeners = listeners;
    }

}
