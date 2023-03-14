package com.ti.apitraining.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.ti.apitraining.Api.VolleyManger;
import com.ti.apitraining.interfaces.ListCallback;
import com.ti.apitraining.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserControllerApi {

    public void getUser(ListCallback<User> userInterface){
        String uri ="https://demo-api.mr-dev.tech/api/users";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                Gson gson = new Gson();
                 JSONArray jsonArray = response.getJSONArray("data");
                 List<User> list = new ArrayList<>();
                 for (int i = 0; i < jsonArray.length(); i++) {
                     User user = gson.fromJson(jsonArray.getJSONObject(i).toString(),User.class);
                     list.add(user);
                 }
                 userInterface.onSuccess(list);
             }catch (JSONException e){
                 userInterface.onFailure("");
             }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                userInterface.onFailure("");
            }
        });

        VolleyManger.getInstance().addToRequestQueue(request);
    }
}
