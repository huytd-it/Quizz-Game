package com.yukihuy.myapplication.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yukihuy.myapplication.Model.User;
import com.yukihuy.myapplication.R;

import ml.huytools.lib.API.ApiOutput;
import ml.huytools.lib.API.ApiProvider;

public class AccountManagerActivity extends AppCompatActivity {
    EditText edtUserName,edtEmail,edtOldPassword,edtNewPassword;
    ImageView imgAvatar;
    public String SHARED_PREFERENCES_NAME="share";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manager);

        edtUserName = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        imgAvatar = findViewById(R.id.imgAvatar);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        String ten = sharedPreferences.getString("ten_dang_nhap","yukihuy");
        String cre = sharedPreferences.getString("email","10000");
        edtUserName.setText(ten);
        edtEmail.setText(cre);
    }
}