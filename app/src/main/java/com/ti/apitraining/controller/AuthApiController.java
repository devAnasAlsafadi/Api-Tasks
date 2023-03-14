package com.ti.apitraining.controller;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.ti.apitraining.Api.VolleyManger;
import com.ti.apitraining.enums.ApiUri;
import com.ti.apitraining.interfaces.InternetConnectionChecker;
import com.ti.apitraining.interfaces.ProcessCallback;
import com.ti.apitraining.models.Student;
import com.ti.apitraining.pref.AppSharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class AuthApiController extends ApiBaseController {

    private static final String TAG = "AuthApiController";




    public void register(Student student, ProcessCallback registerCallback){


        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("full_name",student.fullName);
            jsonObject.put("email",student.email);
            jsonObject.put("password",student.password);
            jsonObject.put("gender",student.gender);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, ApiUri.Register.urn, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String m = response.getString("message");
                        registerCallback.OnSuccess(m);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        registerCallback.OnFailure("");
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    registerCallback.OnFailure(controllerErrorResponse(error));


                }
            });
            VolleyManger.getInstance().addToRequestQueue(request);
        }catch (JSONException e){

        }


    }

    public void login(Student student,ProcessCallback loginCallback){

        checkInternet(new InternetConnectionChecker() {
            @Override
            public void connected() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("email",student.email);
                    jsonObject.put("password",student.password);
                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, ApiUri.Login.urn, jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Gson gson = new Gson();
                            try {
                                Student student1 = gson.fromJson(response.getJSONObject("object").toString(),Student.class);
                                AppSharedPreferences.getInstance().save(student1);
                                String m = response.getString("message");
                                loginCallback.OnSuccess(m);


                            }catch (JSONException e){
                                loginCallback.OnFailure("somethings went wrong, try again ! ");
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            loginCallback.OnFailure(controllerErrorResponse(error));



                        }
                    });
                    VolleyManger.getInstance().addToRequestQueue(request);

                }catch (JSONException e){
                    loginCallback.OnFailure("");

                }
            }

            @Override
            public void connectedError() {
                loginCallback.OnFailure("no internet connection");

            }
        });

    }

    public void logout(ProcessCallback logoutCallback){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, ApiUri.Logout.urn, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                AppSharedPreferences.getInstance().clear();
                try {
                    logoutCallback.OnSuccess(response.getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse.statusCode == 401){
                    AppSharedPreferences.getInstance().clear();
                    logoutCallback.OnSuccess("successfully");
                }else {
                    logoutCallback.OnFailure(controllerErrorResponse(error));
                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getHeadersApi();
            }
        };
        VolleyManger.getInstance().addToRequestQueue(request);

    }
}
