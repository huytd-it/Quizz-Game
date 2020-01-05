package com.yukihuy.myapplication.View;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.github.mikephil.charting.charts.BarChart;
import com.yukihuy.myapplication.API.FieldAPI;
import com.yukihuy.myapplication.Apdapter.FieldListAdapter;

import com.yukihuy.myapplication.Model.Field;
import com.yukihuy.myapplication.R;

import java.util.LinkedList;

import ml.huytools.lib.API.ApiOutput;
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

        FieldAPI userAPI = new FieldAPI(this);
        userAPI.execute();
        App.getInstance().init(this);

    }
    public void LoadComplete(ApiOutput output){
        this.output = output;
        Show();

    }
    public void Show(){
        LinkedList<Field> playTimes = output.toModelManager(Field.class);
        if(playTimes == null){
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
        dialog = new ProgressDialog();
        dialog.show(getSupportFragmentManager(),"YESSSSS");

    }
    public void destroyDialog(){
        dialog.dismiss();
    }

}
