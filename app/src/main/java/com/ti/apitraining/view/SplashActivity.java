package com.ti.apitraining.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.ti.apitraining.databinding.ActivitySplashBinding;
import com.ti.apitraining.pref.AppSharedPreferences;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
    @Override
    protected void onStart() {
        super.onStart();
        controlSplashActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void  controlSplashActivity(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isLoggedIn = AppSharedPreferences.getInstance().isLoggedIn();
                Intent intent =new Intent(SplashActivity.this, isLoggedIn? MainActivity.class: LoginActivity.class);
                startActivity(intent);

            }
        },3000);
    }


}