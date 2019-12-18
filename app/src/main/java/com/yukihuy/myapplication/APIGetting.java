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

    }

    @Override
    protected String doInBackground(String... level) {

        return QuestionAPI.getQuestions(level[0]);
    }

}
