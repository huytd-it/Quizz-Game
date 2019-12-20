package com.yukihuy.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView img_down_arrow;
    Animation animFadein;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_down_arrow =(ImageView) findViewById(R.id.imgArrow);
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        // set animation listener
        img_down_arrow.startAnimation(animFadein);




    }
    public void onClick(View view){
        Intent intent = new Intent(MainActivity.this,QuestionActivity.class);


        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

}
