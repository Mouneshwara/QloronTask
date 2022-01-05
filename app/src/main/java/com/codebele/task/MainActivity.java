package com.codebele.task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.github.rtoshiro.view.video.FullscreenVideoLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,CommentInterFace {
    FirebaseDatabase database;
    String urlvideo =  "https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4";
    private FullscreenVideoLayout videoView;
    Uri videoUri;
    ImageView ivLike,ivDisLike,ivSend;
    EditText etComment;
    TextView tvLike;
    RecyclerView rvComment;
    RelativeLayout rlComment,rlMess,rlLike,rlDisLike;
    CommentAdapter commentAdapter;
    ArrayList<MessagesModel> commentList = new ArrayList<>();
    int m=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
//        myRef.setValue("Hello, mounesh!");

        videoView = (FullscreenVideoLayout) findViewById(R.id.videoview);
        ivLike = (ImageView) findViewById(R.id.iv_like);
        ivDisLike = (ImageView)findViewById(R.id.iv_disLike);
        etComment = (EditText) findViewById(R.id.et_comment);
        rvComment = (RecyclerView) findViewById(R.id.rv_comment);
        ivSend = (ImageView) findViewById(R.id.iv_send);
        rlComment = (RelativeLayout)findViewById(R.id.rl_comment);
        rlMess = (RelativeLayout)findViewById(R.id.rl_mess);
        tvLike = (TextView)findViewById(R.id.tv_like);
        rlLike = (RelativeLayout)findViewById(R.id.rl_like);
        rlDisLike = (RelativeLayout)findViewById(R.id.rl_dislike);
        rlLike.setOnClickListener(this);
        rlDisLike.setOnClickListener(this);
        ivSend.setOnClickListener(this);
        rlComment.setOnClickListener(this);
        firestorevalue();
        comentRecy();
        videoLoad ();
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                if (source.length()>0 &&Character.isWhitespace(source.charAt(0))
                        && etComment.getText().toString().length()==0) {
                    return "";
                }
                return source;
            }
        };
        etComment.setFilters(new InputFilter[]{filter});
    }
    private void videoLoad (){
        videoUri = Uri.parse(urlvideo);
        try {
            videoView.setVideoURI(videoUri);
            videoView.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void like(){
        tvLike.setText("UnLike");
        database.getReference().child("LIKE").child("Video ID 1").child("like").setValue("1");
    }
    private void disLike(){
        tvLike.setText("Like");
        database.getReference().child("LIKE").child("Video ID 1").child("like").setValue("0");
    }

    private void commentRecycler(){
//            commentList.add(new CommentModle(etComment.getText().toString(),""));
//            database.getReference().child("COMMENTES").child("Video ID 1").child("comments").setValue(etComment.getText().toString());
            MessagesModel messagesModel = new MessagesModel(etComment.getText().toString());
            database.getReference().child("COMMENTES").child("Video ID 1").child("1")
                    .push()
                    .setValue(messagesModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                }
            });


    }
    private void comentRecy(){
            commentAdapter = new CommentAdapter(getApplicationContext(),commentList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            rvComment.setLayoutManager(layoutManager);
            rvComment.setAdapter(commentAdapter);
    }
    private void firestorevalue() {
        DatabaseReference chatref = database.getReference().child("COMMENTES").child("Video ID 1").child("1");
        chatref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                commentList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MessagesModel messsages = dataSnapshot.getValue(MessagesModel.class);
                    commentList.add(messsages);
                    Log.d("listmounesh", String.valueOf(commentList));
                    comentRecy();
                    commentAdapter.notifyDataSetChanged();
                    rvComment.scrollToPosition(commentAdapter.getItemCount() - 1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_like:
                like();
                rlLike.setVisibility(View.GONE);
                rlDisLike.setVisibility(View.VISIBLE);
                break;
            case R.id.rl_dislike:
                disLike();
                rlLike.setVisibility(View.VISIBLE);
                rlDisLike.setVisibility(View.GONE);
                break;
            case R.id.iv_send:
                if (!etComment.getText().toString().isEmpty()) {
                    commentRecycler();
                    etComment.setText("");
                }
                break;
            case R.id.rl_comment:
                rlMess.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void commentEditextFoucs() {
        m=1;
        etComment.requestFocus();
    }
}