package com.yukihuy.myapplication;



import android.app.Dialog;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.utils.ColorTemplate;
import com.yukihuy.myapplication.API.Asynctask_DemGio;
import com.yukihuy.myapplication.Apdapter.FieldListAdapter;
import com.yukihuy.myapplication.Model.Field;
import com.yukihuy.myapplication.Model.Question;
import com.yukihuy.myapplication.View.LinhVucActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class QuestionActivity extends AppCompatActivity {
    AnimationDrawable animBlink_color;
    Animation animRotate1, animRotate2,animRotate3,animBlink,animMove;
    ImageView imgClockBig,imgClockMedium,imgClockSmall;
    BarChart barChart;
    ArrayList<BarEntry> barEntries;
    private ImageButton btn_next,btnAskIdeaAudience,btnAskFamylier,btnFiftyPercen,btnBuyCredit;
    protected  TextView tvCreditCurrent;
    Dialog epicDialog;
    ImageView imgCloseDialogt;
    RequestQueue requestQueue;
    String url = "https://cmsandroidgame.000webhostapp.com/api/cau_hoi_theo_linh_vuc?linh_vuc_id=";
    final LinkedList<Question> linkedList = new LinkedList<>();

    int point=0;
    int cur=0;
    TextView m_txt_num;
    TextView m_txt_content;
    TextView m_rad_DA1;
    TextView m_rad_DA2;
    TextView m_rad_DA3;
    TextView m_rad_DA4;
    TextView m_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        m_time = findViewById(R.id.tvTimeLife);
        m_txt_num = findViewById(R.id.tvNumberQuestion);
        m_txt_content = findViewById(R.id.tvNoiDung);
        m_rad_DA1 = (Button) findViewById(R.id.btnDapAnA);
        m_rad_DA2 = (Button) findViewById(R.id.btnDapAnB);
        m_rad_DA3 = (Button) findViewById(R.id.btnDapAnC);
        m_rad_DA4 = (Button) findViewById(R.id.btnDapAnD);
        barChart = findViewById(R.id.bargraph);

        Asynctask_DemGio demGio = new Asynctask_DemGio(m_time);
        demGio.execute();

        //set Animation for ImageView
        Intent intent = getIntent();
        String id = intent.getStringExtra("Message");

        readJSON(id);
        setAnimationImage();

        //Dialog
        epicDialog = new Dialog(this);
        //Chart();
        btnAskIdeaAudience = findViewById(R.id.btnAskAudi);
        btnAskFamylier = findViewById(R.id.btnAskFamilier);
        btnFiftyPercen = findViewById(R.id.btnFiftyPercent);
        btnBuyCredit = findViewById(R.id.btnBuyCredit);



       m_rad_DA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_rad_DA1.setBackgroundResource(R.drawable.blink_color);
                animBlink_color = (AnimationDrawable) m_rad_DA1.getBackground();
                animBlink_color.start();
                //point++;
               // showContent();

            }
        });
        m_rad_DA4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(v);
            }
        });
        btnBuyCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCreditCurrent = findViewById(R.id.tvCreditCurrent);
                int credit = Integer.parseInt(tvCreditCurrent.getText().toString());
                 credit = credit - 200;
                 tvCreditCurrent.setText(String.valueOf(credit));
                 point++;
                 showContent();
            }
        });
        btnFiftyPercen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animMove = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
                m_rad_DA1.startAnimation(animMove);
                m_rad_DA1.setEnabled(false);
                m_rad_DA4.startAnimation(animMove);
                m_rad_DA4.setEnabled(false);
            }
        });
        btnAskIdeaAudience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(v);
            }

        });
        btnAskFamylier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(v);
            }
        });
    }



    public void setAnimationImage() {
        imgClockBig = findViewById(R.id.imgClockbig);
        imgClockMedium= findViewById(R.id.imgClockMedium);
        imgClockSmall = findViewById(R.id.imgClockSmall);

        animRotate1= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        animRotate2= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_2);
        animRotate3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        // set animation listener
        imgClockBig.startAnimation(animRotate1);
        imgClockMedium.startAnimation(animRotate2);
        imgClockSmall.startAnimation(animRotate3);

    }
    public void Chart() {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(2f,10f));
        barEntries.add(new BarEntry(3f,50f));
        barEntries.add(new BarEntry(4f,10f));
        barEntries.add(new BarEntry(5f,30f));

        BarDataSet barDataSet = new BarDataSet(barEntries,"Đáp án");
        //barDataSet.setColors(new int[]{R.color.black,R.color.red,R.color.colorAccent,R.color.colorPrimaryDark});
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextSize(20f);

        YAxis yAxisl = barChart.getAxisLeft();
        yAxisl.setEnabled(false);
        YAxis yAxisr = barChart.getAxisRight();
        yAxisr.setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setEnabled(false);



        BarData barData = new BarData(barDataSet,barDataSet);
        barData.setBarWidth(0.9f);
        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.animateY(5000, Easing.EaseOutBack);
        barChart.invalidate();


    }

    protected void showDialog(View view){
        int id = view.getId();
        switch (id){
            case R.id.btnAskAudi:{
                epicDialog.setContentView(R.layout.ask_audience_dialog);
                imgCloseDialogt = epicDialog.findViewById(R.id.imgClose);
                imgCloseDialogt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        epicDialog.dismiss();
                    }
                });
                break;
            }
            case R.id.btnAskFamilier:{
                epicDialog.setContentView(R.layout.ask_familier_dialog);
                break;
            }
            case R.id.btnDapAnD:{
                epicDialog.setContentView(R.layout.game_over_dialog);
                break;
            }
            default:
                Toast.makeText(this,"Không có lựa chọn",Toast.LENGTH_SHORT);
                break;
        }

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();

    }

    public void showContent(){


        if(linkedList.size() > 0)
        {

            m_txt_num.setText(String.valueOf(point + 1));
            m_txt_content.setText(linkedList.get(point).getNoi_dung());

            m_rad_DA1.setText("A. "+linkedList.get(point).getPhuong_an_A());
            m_rad_DA2.setText("B. "+linkedList.get(point).getPhuong_an_B());
            m_rad_DA3.setText("C. "+linkedList.get(point).getPhuong_an_C());
            m_rad_DA4.setText("D. "+linkedList.get(point).getPhuong_an_D());
        }
        else
        {
            m_txt_content.setText("API can not run.");
            m_txt_num.setVisibility(View.INVISIBLE);
            m_rad_DA1.setVisibility(View.INVISIBLE);
            m_rad_DA2.setVisibility(View.INVISIBLE);
            m_rad_DA3.setVisibility(View.INVISIBLE);
            m_rad_DA4.setVisibility(View.INVISIBLE);
        }
    }


    private void readJSON(String id) {

        requestQueue = Volley.newRequestQueue(this);
        url = url + id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("----------------LIST---------",url);
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject fieldItem = jsonArray.getJSONObject(i);
                        String JsonString = fieldItem.toString();
                        Log.d("----------------LIST---------",JsonString);
                        Question field = Question.ParseJson(Question.class,JsonString);
                        linkedList.addLast(field);
                        Log.d("----------------LIST---------",""+linkedList.get(i).getId());
                    }
                    showContent();
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(QuestionActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }
        );
        requestQueue.add(request);

    }



}

