package com.ti.apitraining.controller;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.ti.apitraining.Api.VolleyManger;
import com.ti.apitraining.interfaces.ListCallback;
import com.ti.apitraining.interfaces.ProcessCallback;
import com.ti.apitraining.models.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TasksApiController extends ApiBaseController {

    public void createTask(String title, ProcessCallback createTasksCallback) {
        String uri = "http://demo-api.mr-dev.tech/api/tasks";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", title);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, uri, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        createTasksCallback.OnSuccess(response.getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        createTasksCallback.OnFailure("");
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    String errorString = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    try {
                        JSONObject jsonObjectError = new JSONObject(errorString);
                        createTasksCallback.OnFailure(jsonObjectError.getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    createTasksCallback.OnFailure("");

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                  return getHeadersApi();
                }
            };
            VolleyManger.getInstance().addToRequestQueue(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void readTasks(ListCallback<Task> listTasksCallback) {
        String uri = "http://demo-api.mr-dev.tech/api/tasks";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Gson gson = new Gson();
                    JSONArray jsonArray = response.getJSONArray("data");
                    List<Task> list = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                       Task task= gson.fromJson(jsonArray.getJSONObject(i).toString(),Task.class);
                        list.add(task);
                    }
                    listTasksCallback.onSuccess(list);
                }catch (JSONException e){
                    listTasksCallback.onFailure("");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String errorString = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject jsonError = new JSONObject(errorString);
                    listTasksCallback.onFailure(jsonError.getString("message"));

                } catch (JSONException e) {
                    e.printStackTrace();
                    listTasksCallback.onFailure("something went wrong, try again !");

                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getHeadersApi();
            }
        };
        VolleyManger.getInstance().addToRequestQueue(request);


    }
    public void updateTasks(String title,int id, ProcessCallback updateCallback) {
        String uri = "http://demo-api.mr-dev.tech/api/tasks/" + String.valueOf(id);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", title);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, uri, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        updateCallback.OnSuccess(response.getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        updateCallback.OnFailure("");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    String errorString = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    try {
                        JSONObject jsonObjectError = new JSONObject(errorString);
                        updateCallback.OnFailure(jsonObjectError.getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    updateCallback.OnFailure("");

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return getHeadersApi();
                }
            };
            VolleyManger.getInstance().addToRequestQueue(request);
        } catch (JSONException e) {

        }
    }
    public void deleteTasks(int id,ProcessCallback deleteCallback) {
        String uri = "http://demo-api.mr-dev.tech/api/tasks/"+String.valueOf(id);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, uri, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    deleteCallback.OnSuccess(response.getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String errorString = new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject jsonObjectError = new JSONObject(errorString);
                    deleteCallback.OnFailure(jsonObjectError.getString("message"));
                } catch (JSONException e) {

                }


            }

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getHeadersApi();
            }
        };
        VolleyManger.getInstance().addToRequestQueue(request);


    }

}