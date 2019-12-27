package com.yukihuy.myapplication.View;

import android.app.job.JobInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yukihuy.myapplication.Apdapter.FieldListAdapter;
import com.yukihuy.myapplication.Apdapter.RankListAdapter;
import com.yukihuy.myapplication.Model.Field;
import com.yukihuy.myapplication.Model.PlayTime;
import com.yukihuy.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

import ml.huytools.lib.API.ApiOutput;
import ml.huytools.lib.API.ApiProvider;
import ml.huytools.lib.App;

public class LinhVucActivity extends AppCompatActivity {
    private RecyclerView rcvFieldList;
    private FieldListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linh_vuc);
        //loadField();
        //dataExample();
        rcvFieldList = findViewById(R.id.rcvField);
        App.getInstance().init(this);
        ApiProvider.Async.GET("/linh_vuc").Then(new ApiProvider.Async.Callback() {
            @Override
            public void OnAPIResult(ApiOutput output, int requestCode) {
                LinkedList<Field> playTimes = output.toModelManager(Field.class);
                mAdapter = new FieldListAdapter(LinhVucActivity.this,playTimes);
                rcvFieldList.setAdapter(mAdapter);
                rcvFieldList.setLayoutManager(new LinearLayoutManager(LinhVucActivity.this));

            }
        });



    }

   /* private void readJSON() {
        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray jsonArray = response.getJSONArray("data");


                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject fieldItem = jsonArray.getJSONObject(i);
                        String JsonString = fieldItem.toString();

                        Field field = Field.ParseJson(Field.class,JsonString);
                        list.add(field);

                        mAdapter = new FieldListAdapter(LinhVucActivity.this, list);
                        rcvFieldList = findViewById(R.id.rcvField);
                        rcvFieldList.setAdapter(mAdapter);
                        rcvFieldList.setLayoutManager(new LinearLayoutManager(LinhVucActivity.this));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LinhVucActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(request);
    }


/*

    public void loadField() {
        String jSonString;
        jSonString = "";

        if (getFieldList(jSonString) == true) {
            Toast.makeText(this, "Load thành công", Toast.LENGTH_SHORT);
        } else {
            Field field = new Field(1, "Thiên văn");
            list.addLast(field);
        }
    }

    public boolean getFieldList(String jsonString) {
        try {
            list = new LinkedList<>();
            JSONArray jsonArray = new JSONArray(jsonString);
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                Field field = Field.ParseJson(Field.class, jsonString);
                list.addLast(field);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

    }

    public void readJSON() {
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.start();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://192.168.56.1:8080/Karma_Laravel/public/api/linh_vuc", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject fieldItem = jsonArray.getJSONObject(i);
                        int id = fieldItem.getInt("id");
                        String ten_linh_vuc = fieldItem.getString("ten_linh_vuc");
                        list.addLast(new Field(id, ten_linh_vuc));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LinhVucActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(request);
    }*/
}
