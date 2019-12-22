package com.yukihuy.myapplication.Apdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.yukihuy.myapplication.APIGetting;
import com.yukihuy.myapplication.Model.Field;
import com.yukihuy.myapplication.QuestionActivity;
import com.yukihuy.myapplication.R;
import com.yukihuy.myapplication.View.LinhVucActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

public class FieldListAdapter extends RecyclerView.Adapter<FieldListAdapter.FieldViewHoder>{
    public Context mContext;
    private ArrayList<Field> mfieldLinkedList;
    private LayoutInflater mInflater;


    public FieldListAdapter(Context context,ArrayList<Field> fieldLinkedList) {
        mInflater = LayoutInflater.from(context);
        this.mfieldLinkedList = fieldLinkedList;
    }

    @NonNull
    @Override
    public FieldViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.field_item,parent,false);
        return new FieldViewHoder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull FieldViewHoder holder, int position) {
        String mCurrent = mfieldLinkedList.get(position).getTen_linh_vuc();
        holder.bntField.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mfieldLinkedList.size();
    }




    public class FieldViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final Button bntField;
        FieldListAdapter mAdapter;
        public FieldViewHoder(@NonNull View itemView, FieldListAdapter adapter) {
            super(itemView);
            bntField = itemView.findViewById(R.id.btnField);
            this.mAdapter = adapter;
            bntField.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                int mPosition = getLayoutPosition();
                Field field = mfieldLinkedList.get(mPosition);
                Log.d("--------------->List","ID = "+field.getId() + " list: " + field.getTen_linh_vuc());
                Intent intent=new Intent(itemView.getContext(), QuestionActivity.class);
                intent.putExtra("Message",String.valueOf(field.getId()));
                Log.d("--------------->List","ID = "+field.getId() + " list: " + field.getTen_linh_vuc());
                itemView.getContext().startActivity(intent);


        }

    }
}
