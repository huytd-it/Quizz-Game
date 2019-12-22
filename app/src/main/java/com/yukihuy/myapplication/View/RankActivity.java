package com.yukihuy.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.yukihuy.myapplication.R;

public class RankActivity extends AppCompatActivity {
    ImageView imgRotate1,imgRotate2,imgRotate3;
    Animation animRotate,animRotate2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        imgRotate3 = findViewById(R.id.imgRotate3);
        imgRotate1 = findViewById(R.id.imgRotate1);
        imgRotate2 = findViewById(R.id.imgRotate2);
        animRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_2);
        imgRotate1.startAnimation(animRotate);
        animRotate2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        imgRotate2.startAnimation(animRotate2);
        animRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        imgRotate3.startAnimation(animRotate);


    }
}
