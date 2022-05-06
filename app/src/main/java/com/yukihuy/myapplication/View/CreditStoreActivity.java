package com.yukihuy.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;


import com.yukihuy.myapplication.Apdapter.CreditListAdapter;

import com.yukihuy.myapplication.Model.Credit;

import com.yukihuy.myapplication.R;

import java.util.LinkedList;

import ml.huytools.lib.API.ApiOutput;
import ml.huytools.lib.API.ApiProvider;
import ml.huytools.lib.App;

public class CreditStoreActivity extends AppCompatActivity {
    private RecyclerView rcvFieldList;
    private CreditListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_sore);
        rcvFieldList = findViewById(R.id.rcvCredit);
        App.getInstance().init(this);
        ApiProvider.Async.GET("/goi_credit").Then(new ApiProvider.Async.Callback() {
            @Override
            public void OnAPIResult(ApiOutput output, int requestCode) {
                LinkedList<Credit> playTimes = output.toModelManager(Credit.class);
                mAdapter = new CreditListAdapter(CreditStoreActivity.this,playTimes);
                rcvFieldList.setAdapter(mAdapter);
                rcvFieldList.setHasFixedSize(true);
                rcvFieldList.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

            }
        });

    }
}
