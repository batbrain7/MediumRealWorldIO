package com.example.mohitkumar.trialapp.data;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mohitkumar.trialapp.core.Login.ILoginModel;
import com.example.mohitkumar.trialapp.core.SignUp.ISignUpModel;
import com.example.mohitkumar.trialapp.data.LoginSignUp.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import static com.android.volley.VolleyLog.TAG;

public class CreatePost {

    public static void newService(String email, String password, ILoginModel.OnLoginFinishedListener loginFinishedListener){
        try {
            String URL = "https://conduit.productionready.io/api/users/login";
            JSONObject jsonBody = new JSONObject();
            JSONObject item = new JSONObject();
            jsonBody.put("user", item);
            item.put("email", email);
            item.put("password", password);

            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("VOLLEYRESPONSE", response);
                    loginFinishedListener.onLoginModelSuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("VOLLEYERROR", error.toString());
                    loginFinishedListener.onError("Unable to login, status code = " + error.networkResponse.statusCode);
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = new String(response.data);

                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };
            VolleySingleton.getInstance().addToRequestQueue(stringRequest);
         //   requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void newSignUpService(String username, String email, String password, ISignUpModel.OnSignUpFinishedListener signUpFinishedListener){
        try {
            String URL = "https://conduit.productionready.io/api/users";
            JSONObject jsonBody = new JSONObject();
            JSONObject item = new JSONObject();
            jsonBody.put("user", item);
            item.put("username", username);
            item.put("email", email);
            item.put("password", password);

            final String requestBody = jsonBody.toString();
            Log.d(TAG, requestBody.toString());

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("VOLLEYRESPONSE", response);
                    signUpFinishedListener.onSignUpModelSuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("VOLLEYERROR", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = new String(response.data);

                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };
            VolleySingleton.getInstance().addToRequestQueue(stringRequest);
            //   requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}