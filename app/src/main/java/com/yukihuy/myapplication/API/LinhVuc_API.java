package com.yukihuy.myapplication.API;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LinhVuc_API{

    public static JSONObject RequestGetLinhVuc  (Context context, String url){

        final JSONObject[] jsonObjects = new JSONObject[1];
        final boolean[] booleans = new boolean[1];
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonObjects[0] = new JSONObject(response);

                    booleans[0]= true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

        while (booleans[0]==true){

        }
        return jsonObjects[0];
    }
}
