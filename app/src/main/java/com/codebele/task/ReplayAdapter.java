package com.codebele.task;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReplayAdapter extends RecyclerView.Adapter<ReplayAdapter.MyViewHolder> {
    Context context;
    ArrayList<ReplyModel> replayList = new ArrayList<>();

    public ReplayAdapter(Context context, ArrayList<ReplyModel> replayList) {
        this.context = context;
        this.replayList = replayList;
    }

    @NonNull
    @Override
    public ReplayAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.layout_replay, parent, false);
        return new ReplayAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReplayAdapter.MyViewHolder holder, int position) {
        holder.tvReplay.setText(replayList.get(position).getReply());

    }

    @Override
    public int getItemCount() {
        return replayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvReplay;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReplay = (TextView)itemView.findViewById(R.id.tv_replay_comment);
        }
    }
}
