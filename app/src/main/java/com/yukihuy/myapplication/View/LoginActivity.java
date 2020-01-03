package com.yukihuy.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import com.yukihuy.myapplication.Model.LoginResponse;
import com.yukihuy.myapplication.Model.User;
import com.yukihuy.myapplication.R;

import ml.huytools.lib.API.ApiConfig;
import ml.huytools.lib.API.ApiOutput;
import ml.huytools.lib.API.ApiParameters;
import ml.huytools.lib.API.ApiProvider;
import ml.huytools.lib.API.JWTAuthenticate;
import ml.huytools.lib.App;


public class LoginActivity extends AppCompatActivity {
    EditText edtUsername,edtPassword;
    Button btnLogin;
     Intent intent = null;
    CheckBox cbxShowPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        App.getInstance().init(this);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        cbxShowPassword = findViewById(R.id.cbxShowPassword);
        btnLogin = findViewById(R.id.btnLogin);

        intent = getIntent();
        String ten_dang_nhap = intent.getStringExtra("ten_dang_nhap");
        String mat_khau = intent.getStringExtra("mat_khau");
        if(ten_dang_nhap!=null && mat_khau !=null){
            edtUsername.setText(ten_dang_nhap);
            edtPassword.setText(mat_khau);
        }

        cbxShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                if(!isChecked){
                    edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });



    }


    public void onClickRegister(View view){
        int id = view.getId();
        switch (id){
            case R.id.btnLogin:
                ApiParameters params = new ApiParameters();
                params.add("ten_dang_nhap",edtUsername.getText().toString());
                params.add("mat_khau",edtPassword.getText().toString());

                ApiProvider.Async.POST("/login").SetParams(params).Then(new ApiProvider.Async.Callback() {
                    @Override
                    public void OnAPIResult(ApiOutput output, int requestCode) {
                        if(output == null || output.Status == false){
                            Toast.makeText(LoginActivity.this, "Login error!", Toast.LENGTH_LONG).show();
                        } else {
                            LoginResponse response =(LoginResponse) output.toModel(LoginResponse.class);
                            ApiConfig.setAuthenticate(new JWTAuthenticate(response.token));
                            intent = new Intent(LoginActivity.this,GameAdminActivity.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "Login success!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                break;
            case R.id.btnLoginFacebook:
                intent = new Intent(LoginActivity.this,GameAdminActivity.class);
                break;
            case R.id.btnLoginGoogle:
                intent = new Intent(LoginActivity.this,GameAdminActivity.class);
                break;
            case R.id.titleRegister:
                intent = new Intent(LoginActivity.this,RegisterActivity.class);
                break;
            case R.id.titleRestore:
                intent = new Intent(LoginActivity.this,GetPassWordActivity.class);
                break;
            default:
                Toast.makeText(this,"Lựa chọn không có sẳn",Toast.LENGTH_LONG);
        }


        if(intent != null){
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
    }
}
