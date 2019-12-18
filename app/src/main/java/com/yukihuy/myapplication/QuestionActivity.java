package com.yukihuy.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    private ImageButton btn_next,btnAskIdeaAudience,btnAskFamylier,btnFiftyPercen;
    Dialog epicDialog;
    ImageView imgCloseDialogt;

    ArrayList<Question> lst_cauhoi;
    int point=0;
    int cur=0;
    TextView m_txt_num;
    TextView m_txt_content;
    TextView m_rad_DA1;
    TextView m_rad_DA2;
    TextView m_rad_DA3;
    TextView m_rad_DA4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        m_txt_num = (TextView) findViewById(R.id.tvCauHoi);
        m_txt_content = (TextView) findViewById(R.id.tvNoiDung);
        m_rad_DA1 = (Button) findViewById(R.id.btnDapAnA);
        m_rad_DA2 = (Button) findViewById(R.id.btnDapAnB);
        m_rad_DA3 = (Button) findViewById(R.id.btnDapAnC);
        m_rad_DA4 = (Button) findViewById(R.id.btnDapAnD);

       // Next();
       //Dialog
        epicDialog = new Dialog(this);
        btnAskIdeaAudience = (ImageButton) findViewById(R.id.btnHoiYKien);

        btnAskIdeaAudience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAskIdeaAudience();
            }
        });


    }

    protected void showAskIdeaAudience(){
        epicDialog.setContentView(R.layout.ask_audience_dialog);
        imgCloseDialogt =(ImageView) epicDialog.findViewById(R.id.imgClose);

        imgCloseDialogt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
            }
        });
        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
    }

    //

    public void Next(){
        Toast.makeText(this,"Bạn đang ở câu hỏi cuối cùng",Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        String jSonString = intent.getStringExtra("Message");

        if(get_lst_cauhoi(jSonString)==true)
        {
            m_txt_num.setText(String.valueOf(point + 1));
            m_txt_content.setText(lst_cauhoi.get(point).getNoiDung());
            m_rad_DA1.setText("A. "+lst_cauhoi.get(point).getDapAn1());
            m_rad_DA2.setText("B. "+lst_cauhoi.get(point).getDapAn2());
            m_rad_DA3.setText("C. "+lst_cauhoi.get(point).getDapAn3());
            m_rad_DA4.setText("D. "+lst_cauhoi.get(point).getDapAn4());
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

    //

    private boolean get_lst_cauhoi(String jSonString) {
        try {
            lst_cauhoi=new ArrayList();
            JSONArray jr = new JSONArray(jSonString);
            int num = jr.length();
            for(int i=0;i<num;i++)
            {
                JSONObject jb = (JSONObject)jr.getJSONObject(i);
                Question quiz=new Question();
                quiz.setNoiDung(jb.getString("NoiDung"));
                quiz.setDapAn1(jb.getString("DapAn1"));
                quiz.setDapAn2(jb.getString("DapAn2"));
                quiz.setDapAn3(jb.getString("DapAn3"));
                quiz.setDapAn4(jb.getString("DapAn4"));
                quiz.setDung(jb.getString("DA_Dung"));
                quiz.setChon("0");
                lst_cauhoi.add(quiz);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
