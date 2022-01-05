package com.codebele.task;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CommentAdapter  extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {
    Context context;
    ArrayList<MessagesModel> commentList = new ArrayList<>();
    String CommentText;

    public CommentAdapter(Context context, ArrayList<MessagesModel> commentList) {
        this.context = context;
        this.commentList = commentList;
    }


    @NonNull
    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.layout_comments, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.MyViewHolder holder, int position) {
        MessagesModel messagesModel = commentList.get(position);
       holder.tvComment.setVisibility(View.VISIBLE);
        holder.tvComment.setText(messagesModel.getComments());
        holder.tvReplay.setVisibility(View.VISIBLE);
//        holder.tvReplay.setText(messagesModel.getReplay());
        holder.tvReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    commentInterFace.commentEditextFoucs();
                Intent i = new Intent(context,ReplayActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);;
                i.putExtra("comm",commentList.get(position).getComments());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvComment,tvReplay;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvComment = (TextView) itemView.findViewById(R.id.tv_comment);
            tvReplay = (TextView) itemView.findViewById(R.id.tv_replay);

        }
    }
}
