package com.yukihuy.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class LinhVucActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linh_vuc);

    }
    public void btnLevel_click(View v) throws ExecutionException, InterruptedException {
        Button btn = (Button)v;
        String linhvuc;
        int Id  = v.getId();
        switch (Id){
            case R.id.btnToan:{
                linhvuc = "1";
                break;
            }
            case R.id.btnHoa:{
                linhvuc = "2";
                break;
            }

            case R.id.btnLichSu:{
                linhvuc = "1";
                break;
            }
            case R.id.btnVan:{
                linhvuc = "2";
                break;
            }
            default:
                linhvuc = "1";
                break;
        }
        String lst=new APIGetting(this).execute(linhvuc).get();
    }
}
