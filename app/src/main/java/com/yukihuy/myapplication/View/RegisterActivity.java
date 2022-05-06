package com.yukihuy.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yukihuy.myapplication.R;

import ml.huytools.lib.API.ApiOutput;
import ml.huytools.lib.API.ApiParameters;
import ml.huytools.lib.API.ApiProvider;

public class RegisterActivity extends AppCompatActivity {
    Intent intent;
    EditText edtUsername,edtPassword,edtRepassword,edtEmail;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        btnRegister = findViewById(R.id.btnRegister);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtRepassword = findViewById(R.id.edtRePassword);
        edtUsername = findViewById(R.id.edtUsername);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiParameters parameters = new ApiParameters();
                parameters.add("ten_dang_nhap",edtUsername.getText().toString());
                parameters.add("mat_khau",edtPassword.getText().toString());
                parameters.add("email",edtEmail.getText().toString());
                Log.d("-----------KQ-------->",edtUsername.getText().toString()+edtEmail.getText().toString()+edtPassword.getText().toString());
                ApiProvider.Async.POST("/register").SetParams(parameters).Then(new ApiProvider.Async.Callback() {
                    @Override
                    public void OnAPIResult(ApiOutput output, int requestCode) {
                        if(output.Status==false||output==null){
                            Toast.makeText(RegisterActivity.this,"Đăng kí không thành công",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            intent.putExtra("ten_dang_nhap",edtUsername.getText().toString());
                            intent.putExtra("mat_khau",edtPassword.getText().toString());
                            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this).toBundle());
                            Toast.makeText(RegisterActivity.this,"Đăng kí thành công",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }

    public void onClickTow(View view){
        int id = view.getId();
        switch (id){
            case R.id.titleRegister:
                intent = new Intent(RegisterActivity.this, RegisterActivity.class);
                break;
            case R.id.titleRestore:
                intent = new Intent(RegisterActivity.this, GetPassWordActivity.class);
                break;
            default:
                Toast.makeText(this,"Lựa chọn không có sẳn",Toast.LENGTH_LONG);
        }


        if(intent != null){
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }

    }
}
