package com.yukihuy.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;


import com.yukihuy.myapplication.Apdapter.RankListAdapter;
import com.yukihuy.myapplication.Model.PlayTime;
import com.yukihuy.myapplication.R;

import java.util.LinkedList;

import ml.huytools.lib.API.ApiOutput;
import ml.huytools.lib.API.ApiProvider;
import ml.huytools.lib.App;


public class RankActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RankListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        recyclerView = findViewById(R.id.rcvRank);
        //setAnimation();
        App.getInstance().init(this);


        ApiProvider.Async.GET("/luot_choi").Then(new ApiProvider.Async.Callback() {
            @Override
            public void OnAPIResult(ApiOutput output, int requestCode) {
                LinkedList<PlayTime> playTimes = output.toModelManager(PlayTime.class);
                mAdapter = new RankListAdapter(RankActivity.this,playTimes);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(RankActivity.this));

            }
        });

    }


    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.fall_down);

        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

}
