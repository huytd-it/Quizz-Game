package com.yukihuy.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.yukihuy.myapplication.Apdapter.HistoryListAdapter;
import com.yukihuy.myapplication.Model.PlayTime;
import com.yukihuy.myapplication.R;

import java.util.LinkedList;

import ml.huytools.lib.API.ApiOutput;
import ml.huytools.lib.API.ApiProvider;

public class PlayGameHistoryActivity extends AppCompatActivity {
    private RecyclerView rcvHistory;
    private HistoryListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game_history);
        rcvHistory = findViewById(R.id.rcvHistory);
        ApiProvider.Async.GET("/lich_su").Then(new ApiProvider.Async.Callback() {
            @Override
            public void OnAPIResult(ApiOutput output, int requestCode) {
                LinkedList<PlayTime> playTimes = output.toModelManager(PlayTime.class);
                mAdapter = new HistoryListAdapter(PlayGameHistoryActivity.this,playTimes);
                rcvHistory.setAdapter(mAdapter);
                rcvHistory.setLayoutManager(new LinearLayoutManager(PlayGameHistoryActivity.this));
            }
        });
    }
}
