package com.ti.apitraining.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ti.apitraining.R;
import com.ti.apitraining.controller.AuthApiController;
import com.ti.apitraining.databinding.ActivityRegisterBinding;
import com.ti.apitraining.interfaces.ProcessCallback;
import com.ti.apitraining.models.Student;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityRegisterBinding binding;
    String gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initializeView();
    }

    private void  initializeView(){
        setOnClickListener();
        controlGenderSelection();
    }
    private void setOnClickListener(){
        binding.btnLoginRegister.setOnClickListener(this);
        binding.imBackLogin.setOnClickListener(this);
        binding.tvRegisterLogin.setOnClickListener(this);

    }

    private void controlGenderSelection(){
        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                gender = i == R.id.male ? "M":"F";
            }
        });
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_Login_register){
            performRegister();
        }else if (view.getId() == R.id.im_back_login){
            intentToLogin();
        }else if (view.getId() == R.id.tv_register_login){
            intentToLogin();
        }
    }

    private void performRegister(){
        if (checkData()){
            register();
        }
    }

    private boolean checkData(){
        String fullName =binding.fullName.getText().toString();
        String email =binding.etEmail.getText().toString();
        String password =binding.etPassword.getText().toString();
        if (!fullName.isEmpty() && !email.isEmpty() && !password.isEmpty() &&gender!=null){
            return true;
        }
        return false;
    }

    private void  register(){
        AuthApiController authApiController = new AuthApiController();
        authApiController.register(getStudent(), new ProcessCallback() {
            @Override
            public void OnSuccess(String message) {
                onBackPressed();
            }

            @Override
            public void OnFailure(String message) {
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private Student getStudent(){
        String fullName = binding.fullName.getText().toString();
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword .getText().toString();
        String gender1 = gender;
        Student student = new Student(fullName,email,gender1,password);
        return student;

    }

    private void intentToLogin(){
        Intent intent  = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }
}