package com.yukihuy.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yukihuy.myapplication.MainActivity;
import com.yukihuy.myapplication.R;

public class GetPassWordActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
    }
    public void onClickTow(View view){

        int id = view.getId();
        switch (id){
            case R.id.titleRegister:
                intent = new Intent(GetPassWordActivity.this, RegisterActivity.class);
                break;
            case R.id.titleRestore:
                intent = new Intent(GetPassWordActivity.this, GetPassWordActivity.class);
                break;
            default:
                Toast.makeText(this,"Lựa chọn không có sẳn",Toast.LENGTH_LONG);
        }


        if(intent != null){
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }

    }
}
