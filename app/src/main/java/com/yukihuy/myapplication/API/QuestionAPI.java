package com.yukihuy.myapplication.API;


import android.app.Activity;
import android.os.AsyncTask;
import com.yukihuy.myapplication.QuestionActivity;


import java.lang.ref.WeakReference;


public class QuestionAPI extends AsyncTask<String,Integer, Void> {
    WeakReference<QuestionActivity> questionActivityWeakReference;
    private Activity mActivity;

    public QuestionAPI(QuestionActivity questionActivity) {
        this.questionActivityWeakReference = new WeakReference<>(questionActivity);
        this.mActivity = questionActivity;
    }

    @Override
    protected Void doInBackground(String... strings) {
        questionActivityWeakReference.get().readJSON(strings[0]);
        return null;
    }
}
