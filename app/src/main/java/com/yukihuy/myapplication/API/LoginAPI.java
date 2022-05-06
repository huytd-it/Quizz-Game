package com.yukihuy.myapplication.API;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;


import com.yukihuy.myapplication.Model.LoginResponse;
import com.yukihuy.myapplication.View.GameAdminActivity;
import com.yukihuy.myapplication.View.LoginActivity;

import java.lang.ref.WeakReference;

import ml.huytools.lib.API.ApiConfig;
import ml.huytools.lib.API.ApiOutput;
import ml.huytools.lib.API.ApiParameters;
import ml.huytools.lib.API.ApiProvider;
import ml.huytools.lib.API.JWTAuthenticate;

public class LoginAPI extends AsyncTask<String,Void, ApiOutput> {
    private Activity mAcitivity;
    private WeakReference<LoginActivity> loginActivityWeakReference;

    public LoginAPI(LoginActivity mAcitivity) {
        this.mAcitivity = mAcitivity;
        loginActivityWeakReference = new WeakReference<>(mAcitivity);
    }

    @Override
    protected void onPreExecute() {
        loginActivityWeakReference.get().showDialog();
    }

    @Override
    protected void onPostExecute(ApiOutput output) {
        if(output == null || output.Status == false){
            Toast.makeText(mAcitivity, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
            loginActivityWeakReference.get().destroyDialog();
        } else {
            LoginResponse response =(LoginResponse) output.toModel(LoginResponse.class);
            ApiConfig.setAuthenticate(new JWTAuthenticate(response.token));
            Intent intent = new Intent(mAcitivity, GameAdminActivity.class);
            mAcitivity.startActivity(intent);
            loginActivityWeakReference.get().destroyDialog();
            Toast.makeText(mAcitivity, "Đăng nhâp thành công", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected ApiOutput doInBackground(String... param) {
        ApiParameters parameters = new ApiParameters();
        parameters.add("ten_dang_nhap",param[0]);
        parameters.add("mat_khau",param[1]);
        return ApiProvider.POST("/login",parameters);
    }
}
