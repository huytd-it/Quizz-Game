package com.yukihuy.myapplication.View;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.yukihuy.myapplication.API.UserAPI;
import com.yukihuy.myapplication.Apdapter.FieldListAdapter;

import com.yukihuy.myapplication.Model.Field;
import com.yukihuy.myapplication.R;

import java.util.ArrayList;
import java.util.LinkedList;

import ml.huytools.lib.API.ApiOutput;
import ml.huytools.lib.API.ApiProvider;
import ml.huytools.lib.App;

public class LinhVucActivity extends AppCompatActivity {
    private RecyclerView rcvFieldList;
    private FieldListAdapter mAdapter;
    private TextView tvThongBao;
    ApiOutput output;
    DialogFragment dialog;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linh_vuc);

        barChart = findViewById(R.id.bargraph);
        rcvFieldList = findViewById(R.id.rcvField);
        tvThongBao = findViewById(R.id.tvThongBao);

        UserAPI userAPI = new UserAPI(this);
        userAPI.execute();
        App.getInstance().init(this);

    }
    public void LoadComplete(ApiOutput output){
        this.output = output;
        Show();

    }
    public void Show(){
        LinkedList<Field> playTimes = output.toModelManager(Field.class);
        if(playTimes.size() <= 0){
           tvThongBao.setText("API Error");
           rcvFieldList.setVisibility(View.INVISIBLE);
        }
        else {
            mAdapter = new FieldListAdapter(LinhVucActivity.this,playTimes);
            rcvFieldList.setAdapter(mAdapter);
            rcvFieldList.setLayoutManager(new LinearLayoutManager(LinhVucActivity.this));
        }

    }
    public void showDialog(){
        dialog = new FireMissilesDialogFragment();
        dialog.show(getSupportFragmentManager(),"YESSSSS");

    }
    public void destroyDialog(){
        dialog.dismiss();
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
