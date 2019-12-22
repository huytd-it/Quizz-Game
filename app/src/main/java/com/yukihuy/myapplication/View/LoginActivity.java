package com.yukihuy.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.yukihuy.myapplication.R;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername,edtPassword;
    Button btnLogin;
    CheckBox cbxShowPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtPassword = findViewById(R.id.edtPassword);
        cbxShowPassword = findViewById(R.id.cbxShowPassword);

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
        Intent intent = new Intent();
        switch (id){
            case R.id.btnLogin:
                intent = new Intent(LoginActivity.this,GameAdminActivity.class);
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

        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }
}
