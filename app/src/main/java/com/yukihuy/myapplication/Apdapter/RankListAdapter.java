package com.yukihuy.myapplication.Apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yukihuy.myapplication.Model.PlayTime;
import com.yukihuy.myapplication.R;

import java.util.LinkedList;

public class RankListAdapter extends RecyclerView.Adapter<RankListAdapter.RankViewHolder> {
    Context context;
    LinkedList<PlayTime> list;
    private LayoutInflater mInflate;



    public RankListAdapter(Context context, LinkedList<PlayTime> list) {
        mInflate = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public RankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.rank_item,parent,false);
        return new RankViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull RankViewHolder holder, int position) {
        PlayTime playTime = list.get(position);
        holder.tvRank.setText(String.valueOf(position + 1));
        holder.tvName.setText(playTime.getTen_dang_nhap());
        holder.tvPoint.setText(String.format("%,d",playTime.getDiem()));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RankViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRank,tvName,tvPoint;

        ImageView imgRotate1,imgRotate2,imgRotate3;
        private RankListAdapter mAdapter;
        public RankViewHolder(@NonNull View itemView,RankListAdapter adapter) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPoint = itemView.findViewById(R.id.tvPoint);
            tvRank = itemView.findViewById(R.id.tvRank);

            this.mAdapter = adapter;
        }

    }
}
