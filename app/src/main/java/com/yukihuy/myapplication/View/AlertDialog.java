package com.yukihuy.myapplication.View;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.yukihuy.myapplication.QuestionActivity;
import com.yukihuy.myapplication.R;

public class AlertDialog extends DialogFragment {
    private Context mContext;
    Button btnketthuc;
    Button btnthemluot;
    TextView tvDiem,tvCau,tvCredit;
    int diem,cau,credit;
    public AlertDialog(Context mContext,int diem, int cau) {
        this.diem = diem;
        this.cau = cau;
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.game_over_dialog,container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try{
            super.onViewCreated(view, savedInstanceState);
            btnketthuc = view.findViewById(R.id.btnketthuc);
            btnthemluot = view.findViewById(R.id.btnthemluotchoi);
            tvCau = view.findViewById(R.id.tvCau);
            tvDiem = view.findViewById(R.id.tvDiem);
            tvCau.setText(String.valueOf(this.cau));
            tvDiem.setText(String.valueOf(this.diem));

            btnketthuc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, GameAdminActivity.class);
                    startActivity(intent);
                }
            });

            btnthemluot.setVisibility(View.INVISIBLE);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
