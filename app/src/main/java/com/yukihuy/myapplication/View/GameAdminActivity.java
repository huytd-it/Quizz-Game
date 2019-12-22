package com.yukihuy.myapplication.View;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yukihuy.myapplication.R;

public class GameAdminActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameadmin);

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
