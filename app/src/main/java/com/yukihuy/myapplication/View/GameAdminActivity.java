package com.yukihuy.myapplication.View;

import android.app.ActivityOptions;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yukihuy.myapplication.Model.User;
import com.yukihuy.myapplication.R;

import ml.huytools.lib.API.ApiOutput;
import ml.huytools.lib.API.ApiProvider;

public class GameAdminActivity extends AppCompatActivity {
    TextView tvUsername,tvCredit;
    ImageView imgAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameadmin);
        tvUsername = findViewById(R.id.tvName);
        tvCredit = findViewById(R.id.tvCredit);
        imgAvatar = findViewById(R.id.imgAvatar);

       ApiProvider.Async.GET("/user").Then(new ApiProvider.Async.Callback() {
            @Override
            public void OnAPIResult(ApiOutput output, int requestCode) {
                User user = (User)output.toModel(User.class);
                tvUsername.setText(user.getTen_dang_nhap());
                tvCredit.setText(String.valueOf(user.getCredit()));
            }
        });

    }
    public void onHomeButtonOnClick(View view){
        int id = view.getId();
        Intent intent = new Intent();
        switch (id){
            case R.id.btnAccount:
                intent = new Intent(GameAdminActivity.this, AccountManagerActivity.class);
                break;
            case R.id.btnHistory:
                intent = new Intent(GameAdminActivity.this,PlayGameHistoryActivity.class);
                break;
            case R.id.btnNewGame:
                intent = new Intent(GameAdminActivity.this, LinhVucActivity.class);
                break;
            case R.id.btnRank:
                intent = new Intent(GameAdminActivity.this,RankActivity.class);
                break;
            case R.id.btnBuyCredit:
                intent = new Intent(GameAdminActivity.this, CreditStoreActivity.class);
                break;
            default:
                Toast.makeText(this,"Lựa chọn không có sẳn",Toast.LENGTH_LONG);
        }

        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

}
