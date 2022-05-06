package com.yukihuy.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.yukihuy.myapplication.View.GetPassWordActivity;

import com.yukihuy.myapplication.View.LinhVucActivity;
import com.yukihuy.myapplication.View.LoginActivity;
import com.yukihuy.myapplication.View.PlayGameHistoryActivity;

import com.yukihuy.myapplication.View.RegisterActivity;

import ml.huytools.lib.API.ApiConfig;


public class MainActivity extends AppCompatActivity {
    ImageView img_down_arrow;
    Animation animFadein;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        config();
        animationArrow();
    }
    private void animationArrow() {
        img_down_arrow =(ImageView) findViewById(R.id.imgArrow);
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        // set animation listener
        img_down_arrow.startAnimation(animFadein);
    }

    public void onClick(View view){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void config(){
        ApiConfig.setHostname("http://192.168.56.1:8080/Karma_Laravel/public/api/");
    }
    public void onClickTow(View view){
        int id = view.getId();
        switch (id){
            case R.id.titleRegister:
                intent = new Intent(MainActivity.this, RegisterActivity.class);
                break;
            case R.id.titleRestore:
                intent = new Intent(MainActivity.this, GetPassWordActivity.class);
                break;
            default:
                Toast.makeText(this,"Lựa chọn không có sẳn",Toast.LENGTH_LONG);
        }


        if(intent != null){
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }

}
}
