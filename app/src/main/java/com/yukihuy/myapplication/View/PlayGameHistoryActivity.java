package com.yukihuy.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.yukihuy.myapplication.Apdapter.HistoryListAdapter;
import com.yukihuy.myapplication.Model.PlayTime;
import com.yukihuy.myapplication.Model.User;
import com.yukihuy.myapplication.R;

import java.util.LinkedList;

import ml.huytools.lib.API.ApiOutput;
import ml.huytools.lib.API.ApiParameters;
import ml.huytools.lib.API.ApiProvider;

public class PlayGameHistoryActivity extends AppCompatActivity {
    private RecyclerView rcvHistory;
    private HistoryListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game_history);
        rcvHistory = findViewById(R.id.rcvHistory);


        ApiProvider.Async.GET("/user").Then(new ApiProvider.Async.Callback() {
            @Override
            public void OnAPIResult(ApiOutput output, int requestCode) {
                User user = (User)output.toModel(User.class);
                Log.d("TAG--------------------------------", "OnAPIResult: "+user.getTen_dang_nhap());
                ApiParameters parameters = new ApiParameters();
                parameters.add("nguoi_choi_id",String.valueOf(user.getId()));

                ApiProvider.Async.POST("/lich_su").SetParams(parameters).Then(new ApiProvider.Async.Callback() {
                    @Override
                    public void OnAPIResult(ApiOutput output, int requestCode) {
                        LinkedList<PlayTime> playTimes = output.toModelManager(PlayTime.class);
                        mAdapter = new HistoryListAdapter(PlayGameHistoryActivity.this,playTimes);
                        rcvHistory.setAdapter(mAdapter);
                        rcvHistory.setLayoutManager(new LinearLayoutManager(PlayGameHistoryActivity.this));
                    }
                });
            }
        });

    }
}
