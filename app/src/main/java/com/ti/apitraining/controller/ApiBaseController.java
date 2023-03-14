package com.ti.apitraining.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.VolleyError;
import com.ti.apitraining.Api.AppController;
import com.ti.apitraining.enums.ApiStateCode;
import com.ti.apitraining.interfaces.InternetConnectionChecker;
import com.ti.apitraining.pref.AppSharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

  class ApiBaseController   {

    protected void checkInternet(InternetConnectionChecker listener){

        ConnectivityManager manager = (ConnectivityManager) AppController.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean state = networkInfo!=null && networkInfo.isConnectedOrConnecting();
        if (state){
            listener.connected();
        }else {
            listener.connectedError();
        }
    }

    protected Map<String,String> getHeadersApi(){
        String token = AppSharedPreferences.getInstance().getToken();
        Map<String,String> headers = new HashMap<>();
        headers.put("Accept","application/json");
        headers.put("authorization",token);
        return headers;
    }

    protected  String controllerErrorResponse(VolleyError error){
        if (error.networkResponse.statusCode == ApiStateCode.BadRequest.code) {
            String errorString = new String(error.networkResponse.data, StandardCharsets.UTF_8);
            try {
                JSONObject jsonError = new JSONObject(errorString);
                return jsonError.getString("message");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "something went wrong, try again !";
    }
}
