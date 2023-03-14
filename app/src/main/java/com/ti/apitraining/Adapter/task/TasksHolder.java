package com.ti.apitraining.Adapter.task;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ti.apitraining.R;
import com.ti.apitraining.databinding.ItemTaskBinding;
import com.ti.apitraining.enums.ActionType;
import com.ti.apitraining.interfaces.TaskCallback;
import com.ti.apitraining.models.Task;

public class TasksHolder extends RecyclerView.ViewHolder  {
    ItemTaskBinding binding;
    TaskCallback taskCallback;
    int idItem;
    public TasksHolder(@NonNull ItemTaskBinding binding,TaskCallback taskCallback) {
        super(binding.getRoot());
        this.binding = binding;


    }

    public void setData(Task tasks){
        binding.tvTitle.setText(tasks.title);
    }



}
