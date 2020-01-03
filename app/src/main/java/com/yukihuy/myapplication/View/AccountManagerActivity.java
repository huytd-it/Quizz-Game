package com.yukihuy.myapplication.View;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manager);

        edtUserName = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        imgAvatar = findViewById(R.id.imgAvatar);

        ApiProvider.Async.GET("/user").Then(new ApiProvider.Async.Callback() {
            @Override
            public void OnAPIResult(ApiOutput output, int requestCode) {
                User user = (User)output.toModel(User.class);
                Log.d("TAG--------------------------------", "OnAPIResult: "+user.getTen_dang_nhap());
                edtUserName.setText(user.getTen_dang_nhap());
                edtEmail.setText(String.valueOf(user.getEmail()));
            }
        });
    }
}