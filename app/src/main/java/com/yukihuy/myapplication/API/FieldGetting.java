package com.yukihuy.myapplication.API;

import android.os.AsyncTask;

public class FieldGetting extends AsyncTask<Void,Void,String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(Void... voids) {
        return FieldAPI.getField();
    }
}
