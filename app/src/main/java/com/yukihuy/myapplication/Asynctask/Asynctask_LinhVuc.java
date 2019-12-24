package com.yukihuy.myapplication.Asynctask;

import android.content.Context;
import android.os.AsyncTask;


import com.yukihuy.myapplication.API.LinhVuc_API;


import org.json.JSONObject;



public class Asynctask_LinhVuc extends AsyncTask<String,Void, JSONObject> {
    public Asynctask_LinhVuc(Context context) {
        this.context = context;
    }
    private Context context;
    @Override
    protected JSONObject doInBackground(String... urls) {
        return LinhVuc_API.RequestGetLinhVuc(context,urls[0]);
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);

    }
}
