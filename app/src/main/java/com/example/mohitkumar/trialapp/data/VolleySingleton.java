package com.example.mohitkumar.trialapp.data;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.mohitkumar.trialapp.MainApplication;

public class VolleySingleton {

    private static VolleySingleton volleySingleton;

    private RequestQueue requestQueue;
    private static Context context;

    public VolleySingleton() {
        this.context = MainApplication.context;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public static synchronized VolleySingleton getInstance() {
        if (volleySingleton == null) {
            volleySingleton = new VolleySingleton();
        }
        return volleySingleton;
    }

    public<T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

}
