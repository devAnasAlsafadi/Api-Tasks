package com.ti.apitraining.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ti.apitraining.Adapter.task.TasksAdapter;
import com.ti.apitraining.R;
import com.ti.apitraining.controller.TasksApiController;
import com.ti.apitraining.databinding.ActivityShowTasksBinding;
import com.ti.apitraining.databinding.CustomeDialogBinding;
import com.ti.apitraining.enums.ActionType;
import com.ti.apitraining.interfaces.FragmentClickListener;
import com.ti.apitraining.interfaces.ListCallback;
import com.ti.apitraining.interfaces.TaskCallback;
import com.ti.apitraining.interfaces.ProcessCallback;
import com.ti.apitraining.models.Task;

import java.util.ArrayList;
import java.util.List;

public class ShowTasks extends AppCompatActivity implements View.OnClickListener {
    ActivityShowTasksBinding binding;
    TasksAdapter adapter;
    List<Task> taskList = new ArrayList<>();
    int idItem;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowTasksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getTaskData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeAdapter();
        initializeView();

    }
    private void initializeView(){
        setOnClickListener();
    }
    private void setOnClickListener(){
        binding.fb.setOnClickListener(this);
        binding.ivBack.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fb){
            Intent intent  = new Intent(getApplicationContext(),AddTasks.class);
            startActivity(intent);
        }else if (view.getId() == R.id.iv_back){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

    }
    private void initializeAdapter(){
        adapter = new TasksAdapter(taskList, new TaskCallback() {
            @Override
            public void onClickDeleteTasks(int id,int pos) {
                idItem = id;
                position = pos;
                deleteTasks(id);
            }
            @Override
            public void onClickUpdateTasks(int id,int pos) {
                Intent intent = new Intent(getApplicationContext(),UpdateTasks.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        binding.rv.setAdapter(adapter);
        binding.rv.setHasFixedSize(true);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
    }
    public void getTaskData(){
        TasksApiController tasksApiController = new TasksApiController();
        tasksApiController.readTasks(new ListCallback<Task>() {
            @Override
            public void onSuccess(List<Task> list) {
                taskList.addAll(list);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(String message) {
                Toast.makeText(ShowTasks.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void deleteTasks(int id){
        TasksApiController apiController = new TasksApiController();
        apiController.deleteTasks(id, new ProcessCallback() {
            @Override
            public void OnSuccess(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                taskList.remove(position);
                adapter.notifyItemRemoved(position);
            }

            @Override
            public void OnFailure(String message) {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }




}