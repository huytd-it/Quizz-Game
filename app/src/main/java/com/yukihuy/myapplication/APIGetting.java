package com.yukihuy.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class APIGetting extends AsyncTask<String, String, String> {
    private Context m_con;
    public APIGetting(Context con)
    {
        m_con=con;
    }
    WeakReference<TextView> demgio;
    APIGetting(TextView tv){
        demgio = new WeakReference<>(tv);
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Intent intent=new Intent(m_con,QuestionActivity.class);
        intent.putExtra("Message",s);

        Activity activity=(Activity)m_con;
        activity.startActivity(intent);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        demgio.get().setText(values[0]);
    }

    @Override
    protected String doInBackground(String... level) {
        for(int i = 1;i<=30;i++){
            try {
                Thread.sleep(1000);
                publishProgress(i+"");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return QuestionAPI.getQuestions(level[0]);
    }

}
