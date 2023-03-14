package com.ti.apitraining.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.ti.apitraining.Adapter.task.TasksAdapter;
import com.ti.apitraining.R;
import com.ti.apitraining.controller.TasksApiController;
import com.ti.apitraining.databinding.ActivityAddTasksBinding;
import com.ti.apitraining.interfaces.ProcessCallback;
import com.ti.apitraining.interfaces.TaskCallback;
import com.ti.apitraining.models.Task;

import java.util.ArrayList;
import java.util.List;

public class AddTasks extends AppCompatActivity implements View.OnClickListener {
    ActivityAddTasksBinding binding;

    int idItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTasksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeView();

    }



    private void initializeView(){
        setOnClickListener();
    }
    private void setOnClickListener(){
        binding.btnAdd.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_add){
            performAddTasks();
        }

    }

    private void performAddTasks(){
        if (checkData()){
            addTasks();
        }
    }

    private boolean checkData(){
        if (!binding.etAddTasks.getText().toString().isEmpty()){
            return true;
        }
        Snackbar.make(binding.getRoot(),"Enter a Data : ",Snackbar.LENGTH_LONG).show();
        return false;
    }

    private void addTasks(){
        TasksApiController apiController = new TasksApiController();
        String title = binding.etAddTasks.getText().toString();
        apiController.createTask(title, new ProcessCallback() {
            @Override
            public void OnSuccess(String message) {
                Intent intent  = new Intent(getApplicationContext(),ShowTasks.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void OnFailure(String message) {

            }
        });
    }
}