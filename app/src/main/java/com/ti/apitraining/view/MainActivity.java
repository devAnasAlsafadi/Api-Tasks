package com.ti.apitraining.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.ti.apitraining.Adapter.users.UserAdapter;
import com.ti.apitraining.R;
import com.ti.apitraining.controller.AuthApiController;
import com.ti.apitraining.controller.UserControllerApi;
import com.ti.apitraining.databinding.ActivityMainBinding;
import com.ti.apitraining.interfaces.ListCallback;
import com.ti.apitraining.interfaces.ProcessCallback;
import com.ti.apitraining.models.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    UserAdapter adapter;
    List<User> userList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolBar);
        getUserData();
        initializeAdapter();
    }



    private void initializeAdapter(){
        adapter = new UserAdapter(userList);
        binding.rv.setAdapter(adapter);
        binding.rv.setHasFixedSize(true);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.logOut:
                logout();
                break;
            case R.id.viewtasks:
                Intent intent  = new Intent(getApplicationContext(),ShowTasks.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);

    }


    public void getUserData(){
        UserControllerApi userControllerApi = new UserControllerApi();
        userControllerApi.getUser(new ListCallback<User>() {
            @Override
            public void onSuccess(List<User> list) {
                userList.addAll(list);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    private void logout(){
        AuthApiController authApiController = new AuthApiController();
        authApiController.logout(new ProcessCallback() {
            @Override
            public void OnSuccess(String message) {
                Intent intent  = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void OnFailure(String message) {

            }
        });

    }
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}