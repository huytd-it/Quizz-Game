package com.yukihuy.myapplication.API;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class Asynctask_DemGio extends AsyncTask<Void,String,String> {

    WeakReference<TextView> textViewWeakReference;
    public Asynctask_DemGio(TextView textView) {
        textViewWeakReference = new WeakReference<>(textView);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        textViewWeakReference.get().setText(values[0]);
    }

    @Override
    protected String doInBackground(Void... voids) {
        for(int i = 1 ;i<=30;i++){
            publishProgress(i+"");
            SystemClock.sleep(1000);
        }
        return null;
    }
}
