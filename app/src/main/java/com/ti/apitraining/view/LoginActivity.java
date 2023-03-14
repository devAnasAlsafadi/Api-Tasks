package com.ti.apitraining.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.ti.apitraining.R;
import com.ti.apitraining.controller.AuthApiController;
import com.ti.apitraining.databinding.ActivityLoginBinding;
import com.ti.apitraining.interfaces.ProcessCallback;
import com.ti.apitraining.models.Student;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeViews();
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    protected void initializeViews(){
        setOnClickListeners();
    }
    private void setOnClickListeners(){
        binding.btnLogin.setOnClickListener(this);
        binding.tvCreateAccount.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_Login){
            performsLogin();
            finish();
        }else if (view.getId() == R.id.tv_create_account){
            Intent intent  = new Intent(getApplicationContext(),RegisterActivity.class);
            startActivity(intent);
        }
    }

    private void performsLogin(){
        if (checkData()){
            login();
        }
    }

    private  boolean checkData(){
        String email = binding.email.getText().toString();
        String password = binding.etPassword.getText().toString();
        if (!email.isEmpty() && !password.isEmpty()){
            return true;
        }

        Snackbar.make(binding.getRoot(),"enter data : ",Snackbar.LENGTH_LONG).show();
        return false;
    }

    private void login(){
        AuthApiController authApiController = new AuthApiController();
        String email = binding.email.getText().toString();
        String password = binding.etPassword.getText().toString();
        Student student =new Student(email,password);
        authApiController.login(student, new ProcessCallback() {
            @Override
            public void OnSuccess(String message) {

                Intent intent  = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void OnFailure(String message) {

                Snackbar.make(binding.getRoot(),message,Snackbar.LENGTH_LONG).show();
            }
        });
    }
}