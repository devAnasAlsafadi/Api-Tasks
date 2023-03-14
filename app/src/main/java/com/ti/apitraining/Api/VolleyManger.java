package com.ti.apitraining.Api;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyManger {

    private static VolleyManger instance;
    private RequestQueue requestQueue;

    private VolleyManger() {
    }

    public static synchronized VolleyManger getInstance() {
        if (instance==null){
            instance = new VolleyManger();
        }
        return instance;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(AppController.getInstance());
        }
        return requestQueue;
    }


    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
