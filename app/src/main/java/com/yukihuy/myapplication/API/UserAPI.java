package com.yukihuy.myapplication.API;
import android.os.AsyncTask;

import com.yukihuy.myapplication.View.LinhVucActivity;

import java.lang.ref.WeakReference;

import ml.huytools.lib.API.ApiOutput;
import ml.huytools.lib.API.ApiProvider;

public class UserAPI extends AsyncTask<Void,Integer,ApiOutput> {
    WeakReference<LinhVucActivity>linhVucActivityWeakReference;

    public UserAPI(LinhVucActivity linhVucActivity) {
        linhVucActivityWeakReference = new WeakReference<>(linhVucActivity);
    }

    @Override
    protected void onPreExecute() {
        linhVucActivityWeakReference.get().showDialog();
    }

    @Override
    protected void onPostExecute(ApiOutput apiOutput) {
        super.onPostExecute(apiOutput);
        linhVucActivityWeakReference.get().destroyDialog();
        linhVucActivityWeakReference.get().LoadComplete(apiOutput);
    }

    @Override
    protected ApiOutput doInBackground(Void... voids) {
        return  ApiProvider.GET("/linh_vuc");

    }
}
