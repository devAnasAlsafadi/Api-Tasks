package com.ti.apitraining.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.ti.apitraining.Adapter.task.TasksAdapter;
import com.ti.apitraining.R;
import com.ti.apitraining.controller.TasksApiController;
import com.ti.apitraining.databinding.ActivityUpdateTasksBinding;
import com.ti.apitraining.enums.ActionType;
import com.ti.apitraining.interfaces.ProcessCallback;
import com.ti.apitraining.interfaces.TaskCallback;
import com.ti.apitraining.models.Task;

import java.util.ArrayList;

public class UpdateTasks extends AppCompatActivity implements View.OnClickListener{
    ActivityUpdateTasksBinding binding;
    ArrayList<Task> tasks = new ArrayList<>();
    TasksAdapter adapter;
    int idItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityUpdateTasksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeView();
        Toast.makeText(UpdateTasks.this, getId()+"", Toast.LENGTH_SHORT).show();

    }


    private void initializeView(){
        setOnClickListener();
    }
    private void setOnClickListener(){
        binding.btnUpdate.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_update){
            performAddTasks();
            onBackPressed();

        }

    }

    private void performAddTasks(){
        if (checkData()){
            updateTasks();
        }
    }

    private boolean checkData(){
        if (!binding.etUpdateTasks.getText().toString().isEmpty()){
            return true;
        }
        Snackbar.make(binding.getRoot(),"Enter a Data : ",Snackbar.LENGTH_LONG).show();
        return false;
    }

    private int getId(){
        Intent intent = getIntent();
         return intent.getIntExtra("id",0);
    }

    private void updateTasks(){
        TasksApiController apiController = new TasksApiController();
        String title = binding.etUpdateTasks.getText().toString();
        apiController.updateTasks(title,getId(), new ProcessCallback() {
            @Override
            public void OnSuccess(String message) {
                Toast.makeText(getApplicationContext(),"تم التعديل", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnFailure(String message) {
            }
        });
    }



}