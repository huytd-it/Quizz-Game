package com.yukihuy.myapplication.View;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.yukihuy.myapplication.API.UserAPI;
import com.yukihuy.myapplication.Model.User;
import com.yukihuy.myapplication.R;

import ml.huytools.lib.API.ApiOutput;
import ml.huytools.lib.API.ApiProvider;

public class GameAdminActivity extends AppCompatActivity {
    TextView tvUsername,tvCredit;
    ImageView imgAvatar;
    DialogFragment dialog;
    public String SHARED_PREFERENCES_NAME="share";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameadmin);
        tvUsername = findViewById(R.id.tvName);
        tvCredit = findViewById(R.id.tvCredit);
        imgAvatar = findViewById(R.id.imgAvatar);
        UserAPI userAPI = new UserAPI(this);
        userAPI.execute();



    }
    public void LoadComplete(ApiOutput output){
        User user = (User)output.toModel(User.class);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        if(user!=null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("ten_dang_nhap",user.getTen_dang_nhap());
            editor.putString("credit",String.valueOf(user.getCredit()));
            editor.putString("email",user.getEmail());
            editor.commit();
        }
        String ten = sharedPreferences.getString("ten_dang_nhap","yukihuy");
        String cre = sharedPreferences.getString("credit","10000");
        tvUsername.setText(ten);
        tvCredit.setText(cre);
    }
    public void showDialog(){
        dialog = new ProgressDialog();
        dialog.show(getSupportFragmentManager(),"YESSSSS");

    }
    public void destroyDialog(){
        dialog.dismiss();
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
