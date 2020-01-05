package com.yukihuy.myapplication;



import android.app.Dialog;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;

import android.os.Bundle;

import android.os.CountDownTimer;
import android.os.SystemClock;
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
import androidx.fragment.app.DialogFragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.data.BarEntry;

import com.yukihuy.myapplication.API.Asynctask_DemGio;
import com.yukihuy.myapplication.API.QuestionAPI;
import com.yukihuy.myapplication.Model.Question;

import com.yukihuy.myapplication.View.AlertDialog;
import com.yukihuy.myapplication.View.AskAudicentDialog;
import com.yukihuy.myapplication.View.GameAdminActivity;
import com.yukihuy.myapplication.View.ProgressDialog;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Clock;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;


public class QuestionActivity extends AppCompatActivity {
    AnimationDrawable animBlink_color;
    Animation animRotate1, animRotate2,animRotate3,animBlink,animMove;
    ImageView imgClockBig,imgClockMedium,imgClockSmall;
    BarChart barChart;
    ArrayList<BarEntry> barEntries;
    private ImageButton btnAskIdeaAudience,btnAskFamylier,btnFiftyPercen,btnBuyCredit;
    protected  TextView tvCreditCurrent;
    Dialog epicDialog;
    DialogFragment dialog;
    ImageView imgCloseDialogt;
    RequestQueue requestQueue;
    String url = "http://192.168.56.1:8080/Karma_Laravel/public/api/cau_hoi_theo_linh_vuc?linh_vuc_id=";
    final LinkedList<Question> linkedList = new LinkedList<>();
    CountDownTimer cd,cd2;
    int point=0;
    TextView m_txt_num;
    TextView m_txt_content;
    TextView m_rad_DA1;
    TextView m_rad_DA2;
    TextView m_rad_DA3;
    TextView m_rad_DA4;
    TextView m_time;
    String dap_an ="";

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
        countdown();


        //set Animation for ImageView
        Intent intent = getIntent();
        String id = intent.getStringExtra("Message");
        //Xu li Json
        QuestionAPI questionAPI = new QuestionAPI(this);
        questionAPI.execute(id);
       /* Asynctask_DemGio demGio = new Asynctask_DemGio(m_time,this);
        demGio.execute();*/

        setAnimationImage();
        //Dialog
        epicDialog = new Dialog(this);

        //Chart();
        btnAskIdeaAudience = findViewById(R.id.btnAskAudi);
        btnAskFamylier = findViewById(R.id.btnAskFamilier);
        btnFiftyPercen = findViewById(R.id.btnFiftyPercent);
        btnBuyCredit = findViewById(R.id.btnBuyCredit);

        //Xu li Button tra loi cau hoi
        {
        m_rad_DA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = m_rad_DA1.getText().length();
                String tl = (String) m_rad_DA1.getText().subSequence(3, length);
                answerQuiz(view, tl);
            }
        });
        m_rad_DA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = m_rad_DA2.getText().length();
                String tl = (String) m_rad_DA2.getText().subSequence(3, length);
                answerQuiz(view,tl);
            }
        });
        m_rad_DA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = m_rad_DA3.getText().length();
                String tl = (String) m_rad_DA3.getText().subSequence(3, length);
                answerQuiz(view,tl);
            }
        });
        m_rad_DA4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = m_rad_DA4.getText().length();
                String tl = (String) m_rad_DA4.getText().subSequence(3, length);
                answerQuiz(view,tl);
            }
        });
        }
        btnBuyCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCreditCurrent = findViewById(R.id.tvCreditCurrent);
                int credit = Integer.parseInt(tvCreditCurrent.getText().toString());
                if(credit>100*point){
                    credit = credit - 100*point;
                    tvCreditCurrent.setText(String.valueOf(credit));
                    point++;
                    showContent();
                }
                else
                    Toast.makeText(QuestionActivity.this,"Bạn không đủ credit",Toast.LENGTH_SHORT).show();

            }
        });
        btnFiftyPercen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* int dem = 0;
                int an;

                int alength = m_rad_DA1.getText().length();
                String atl = (String) m_rad_DA1.getText().subSequence(3,alength);
                int blenght = m_rad_DA2.getText().length();
                String btl = (String) m_rad_DA2.getText().subSequence(3,blenght);
                int clenght = m_rad_DA3.getText().length();
                String ctl = (String) m_rad_DA3.getText().subSequence(3,clenght);
                int dlenght = m_rad_DA4.getText().length();
                String dtl = (String) m_rad_DA4.getText().subSequence(3,dlenght);*/
                animMove = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
                m_rad_DA1.startAnimation(animMove);
                m_rad_DA3.startAnimation(animMove);
                btnFiftyPercen.setEnabled(false);
                btnFiftyPercen.setBackgroundResource(R.drawable.button_black);
            }
        });
        btnAskIdeaAudience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AskAudicentDialog();
                dialog.show(getSupportFragmentManager(),"YESSSSS");
                btnAskIdeaAudience.setEnabled(false);
                btnAskIdeaAudience.setBackgroundResource(R.drawable.button_black);
            }
        });
        btnAskFamylier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(view);
                btnAskFamylier.setEnabled(false);
                btnAskFamylier.setBackgroundResource(R.drawable.button_black);
            }
        });
    }

    private void answerQuiz(View view, String tl) {
        if (tl.equals(dap_an)) {
            int id = view.getId();
            Button button =(Button) findViewById(id);
            button.setBackgroundResource(R.drawable.blink_color);
            animBlink_color = (AnimationDrawable)button.getBackground();
            animBlink_color.start();
            //animBlink_color.getDuration(1000);
            cd2 = new CountDownTimer(2000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    point++;
                    showContent();
                }
            }.start();

        } else {
            showDialog(view);
        }
    }

    //SetAnimationImage
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

    //Dialog
    protected void showDialog(View view){
        switch (view.getId()){

            case R.id.btnDapAnA:{
                endGame();
                break;
            }
            case R.id.btnDapAnB:{
                endGame();
                break;
            }
            case R.id.btnDapAnC:{
                endGame();
                break;
            }
            case R.id.btnDapAnD:{
                endGame();
                break;
            }
            case R.id.btnAskAudi:{
                epicDialog.setContentView(R.layout.ask_audience_dialog);
                imgCloseDialogt = epicDialog.findViewById(R.id.imgClose);
                imgCloseDialogt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        epicDialog.dismiss();
                    }
                });
                epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                epicDialog.show();
                break;
            }
            case R.id.btnAskFamilier:{
                epicDialog.setContentView(R.layout.ask_familier_dialog);
                TextView ask = epicDialog.findViewById(R.id.tvDapAnNguoiThan);
                Random random = new Random();
                String answer  = "";
                int da = 1+random.nextInt(4);
                switch (da){
                    case 1:
                        answer = "A";
                        break;
                    case 2:
                        answer = "B";
                        break;
                    case 3:
                        answer = "C";
                        break;
                    case 4:
                        answer = "D";
                        break;
                        default:
                            break;
                }
                answer = "D";
                ask.setText(answer);
                Button button = epicDialog.findViewById(R.id.buttonthemluotchoi);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        epicDialog.dismiss();
                    }
                });
                epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                epicDialog.show();
                break;
            }
            default:
                Toast.makeText(this,"Không có lựa chọn",Toast.LENGTH_SHORT);
                break;
        }

    }

    public void endGame() {
        showEndGameDialog();
    }

    //Show Cau Hoi
    public void showContent(){
        //thiết lập lại button đáp án
        m_rad_DA1.setEnabled(true);
        m_rad_DA2.setEnabled(true);
        m_rad_DA3.setEnabled(true);
        m_rad_DA4.setEnabled(true);
        m_rad_DA1.setBackgroundResource(R.drawable.button_red);
        m_rad_DA2.setBackgroundResource(R.drawable.button_red);
        m_rad_DA3.setBackgroundResource(R.drawable.button_red);
        m_rad_DA4.setBackgroundResource(R.drawable.button_red);
        cd.cancel();
        countdown();
        animMove = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        m_rad_DA1.startAnimation(animMove);
        m_rad_DA2.startAnimation(animMove);
        m_rad_DA3.startAnimation(animMove);
        m_rad_DA4.startAnimation(animMove);
        //Gán giá trị cho button
        if(point <=14){
            if(linkedList != null)
            {
                m_txt_num.setText(String.valueOf(point + 1));
                m_txt_content.setText(linkedList.get(point).getNoi_dung());
                m_rad_DA1.setText("A. "+linkedList.get(point).getPhuong_an_A());
                m_rad_DA2.setText("B. "+linkedList.get(point).getPhuong_an_B());
                m_rad_DA3.setText("C. "+linkedList.get(point).getPhuong_an_C());
                m_rad_DA4.setText("D. "+linkedList.get(point).getPhuong_an_D());
                dap_an = linkedList.get(point).getDap_an();
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
        else{
            showEndGameDialog();
        }

    }

    //JSonObject
    public void readJSON(String id) {
        requestQueue = Volley.newRequestQueue(this);
        url = url + id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

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

    public void showDialogFragment(){
        dialog = new ProgressDialog();
        dialog.show(getSupportFragmentManager(),"YESSSSS");

    }
    public void destroyDialog(){
        dialog.dismiss();
    }
    public void showEndGameDialog(){
        dialog = new AlertDialog(this,point*1000,point);
        try{
            dialog.show(getSupportFragmentManager(),"YESSSSS");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void countdown(){
        cd = new CountDownTimer(31000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                m_time.setText  (String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                showEndGameDialog();
            }
        }.start();
    }
}

