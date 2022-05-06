package com.yukihuy.myapplication.Apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yukihuy.myapplication.Model.PlayTime;
import com.yukihuy.myapplication.R;

import java.util.LinkedList;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder> {
    private Context mContext;
    private LinkedList<PlayTime> playTimeLinkedList;
    private LayoutInflater mInflater;

    public HistoryListAdapter(Context mContext, LinkedList<PlayTime> playTimeLinkedList) {
        this.playTimeLinkedList = playTimeLinkedList;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.history_item,parent,false);
        return new HistoryViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        PlayTime playTime = playTimeLinkedList.get(position);
        holder.tvNumber.setText("Số câu: "+playTime.getSo_cau());
        holder.tvPoint.setText(String.format("%,d",playTime.getDiem())+" điểm");
        holder.tvDate.setText(playTime.getNgay_gio());

    }

    @Override
    public int getItemCount() {
        return playTimeLinkedList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDate,tvPoint,tvNumber;
        private HistoryListAdapter mAdapter;


        public HistoryViewHolder(@NonNull View itemView,HistoryListAdapter adapter) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvTime);
            tvPoint = itemView.findViewById(R.id.tvPoint);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            this.mAdapter = adapter;

        }
    }
}
