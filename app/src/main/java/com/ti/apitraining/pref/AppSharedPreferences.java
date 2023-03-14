package com.ti.apitraining.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.ti.apitraining.Api.AppController;
import com.ti.apitraining.enums.PrefKeys;
import com.ti.apitraining.models.Student;

public class AppSharedPreferences {


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static AppSharedPreferences instance;


    private AppSharedPreferences(Context context) {
       sharedPreferences =  context.getSharedPreferences("prefs_student",Context.MODE_PRIVATE);
    }


    public static AppSharedPreferences getInstance() {
        if (instance == null){
            instance = new AppSharedPreferences(AppController.getInstance());
        }
        return instance;
    }

    public void save(Student student){
        editor = sharedPreferences.edit();
        editor.putInt(PrefKeys.id.name(),student.id);
        editor.putBoolean(PrefKeys.loggedIn.name(),true);
        editor.putString(PrefKeys.email.name(), student.email);
        editor.putString(PrefKeys.fullName.name(), student.fullName);
        editor.putString(PrefKeys.token.name(), "Bearer "+student.token);
        editor.apply();
    }
    public  boolean isLoggedIn(){
        return sharedPreferences.getBoolean(PrefKeys.loggedIn.name(), false);
    }

    public void clear(){
        editor =sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public  String getToken(){
        return sharedPreferences.getString(PrefKeys.token.name(), "");
    }
}
