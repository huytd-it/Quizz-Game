package com.yukihuy.myapplication.View;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.yukihuy.myapplication.Apdapter.FieldListAdapter;
import com.yukihuy.myapplication.Asynctask.Asynctask_LinhVuc;


import com.yukihuy.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LinhVucActivity extends AppCompatActivity {

    private String url = "https://cmsandroidgame.000webhostapp.com/api/linh_vuc";
    RecyclerView rcvFieldList;
    FieldListAdapter mAdapter;
    ArrayList<JSONObject> list = new ArrayList<JSONObject>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linh_vuc);
        rcvFieldList = findViewById(R.id.rcvField);
        Asynctask_LinhVuc asynctask_linhVuc = new Asynctask_LinhVuc(this){
            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);
                try {
                    if(jsonObject.getBoolean("success")== true){
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0;i<jsonArray.length();i++){
                            list.add(jsonArray.getJSONObject(i));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        asynctask_linhVuc.execute(url);
        mAdapter = new FieldListAdapter(this,list);
        rcvFieldList.setAdapter(mAdapter);
        rcvFieldList.setLayoutManager(new LinearLayoutManager(LinhVucActivity.this));
    }
/*
    private void readJSON() {
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
    }*/
}
