package com.yukihuy.myapplication.Apdapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yukihuy.myapplication.Model.Credit;
import com.yukihuy.myapplication.R;
import com.yukihuy.myapplication.View.GameAdminActivity;

import java.util.LinkedList;


public class CreditListAdapter extends RecyclerView.Adapter<CreditListAdapter.CreditViewHolder>{
    public Context mContext;
    private LinkedList<Credit> mList;
    private LayoutInflater mInflater;


    public CreditListAdapter(Context context, LinkedList<Credit> fieldLinkedList) {
        mInflater = LayoutInflater.from(context);
        this.mList = fieldLinkedList;
    }

    @NonNull
    @Override
    public CreditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.credit_item,parent,false);
        return new CreditViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditViewHolder holder, int position) {
        Credit credit = mList.get(position);
        holder.tvMoney.setText("$"+ String.format("%,d",credit.getSo_tien()));
        holder.tvCredit.setText(""+credit.getCredit());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }




    public class CreditViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public  TextView tvMoney,tvCredit;
        CreditListAdapter mAdapter;
        public String SHARED_PREFERENCES_NAME="share";

        public CreditViewHolder(@NonNull View itemView, CreditListAdapter adapter) {
            super(itemView);
            tvCredit = itemView.findViewById(R.id.tvCredit);
            tvMoney = itemView.findViewById(R.id.tvMoney);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                int mPosition = getLayoutPosition();
                Credit field = mList.get(mPosition);
                Intent intent=new Intent(itemView.getContext(), GameAdminActivity.class);
                itemView.getContext().startActivity(intent);

        }


    }
}
