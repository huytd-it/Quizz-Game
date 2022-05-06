package com.yukihuy.myapplication.API;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.widget.TextView;

import com.yukihuy.myapplication.QuestionActivity;

import java.lang.ref.WeakReference;

public class Asynctask_DemGio extends AsyncTask<Void,String,String> {
    WeakReference<QuestionActivity> questionActivityWeakReference;
    WeakReference<TextView> textViewWeakReference;
    public Asynctask_DemGio(TextView textView,QuestionActivity questionActivity) {
        textViewWeakReference = new WeakReference<>(textView);
        questionActivityWeakReference = new WeakReference<>(questionActivity);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {

    }

    @Override
    protected void onProgressUpdate(String... values) {
        textViewWeakReference.get().setText(""+values[0]);
    }

    @Override
    protected String doInBackground(Void... voids) {
        for(int i = 60 ;i>=1;i--){
            try{
                publishProgress(i+"");
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
        return null;

    }
}
