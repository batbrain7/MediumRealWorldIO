package com.example.mohitkumar.trialapp.data;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.example.mohitkumar.trialapp.core.Login.ILoginModel;
import com.example.mohitkumar.trialapp.core.SignUp.ISignUpModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class CreateSignUp {

    public static void newService(String username, String email, String password, ISignUpModel.OnSignUpFinishedListener signUpFinishedListener){
        try {
            String URL = "https://conduit.productionready.io/api/users";
            JSONObject jsonBody = new JSONObject();
            JSONObject item = new JSONObject();
            jsonBody.put("user", item);
            item.put("username", username);
            item.put("email", email);
            item.put("password", password);

            final String requestBody = jsonBody.toString();

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
                    signUpFinishedListener.onError("The user already exists, response code = " + error.networkResponse.statusCode);
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