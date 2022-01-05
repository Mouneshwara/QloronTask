package com.codebele.qlorontask.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.codebele.qlorontask.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_SHORT;


public class VideoActivity extends AppCompatActivity implements View.OnClickListener{
    //    @BindView(R.id.video)
//    VideoView videoView;
    String videoUrl = "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1";
    Integer likeCount = 0;
    ImageView ivLike;
    FirebaseDatabase db;
    VideoView videoView;
    Button btnSubmit;
    FirebaseDatabase database;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
//        ButterKnife.bind(this);
        videoView = (VideoView) findViewById(R.id.video);
        Uri uri = Uri.parse(videoUrl);
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
        init();
        DAOVideo daoVideo = new DAOVideo();
        btnSubmit.setOnClickListener(this);
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        likeCount = likeCount + 1;
//                        VideoTable videoTable = new VideoTable("Hi", "Hello", "likeCount");
//                        daoVideo.add(videoTable).addOnSuccessListener(suc ->
//                        {
//                            Toast.makeText(getApplicationContext(), "Hello Data Saved", Toast.LENGTH_SHORT).show();
//                        }).addOnFailureListener(er ->
//                                {
//                                    Toast.makeText(getApplicationContext(), "Hello Data Not Saved", Toast.LENGTH_SHORT).show();
//                                }
//                        );
//                    }
//                });
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("Hello, World!");
    }

    private void init() {
        ivLike = (ImageView) findViewById(R.id.iv_like);
        btnSubmit = (Button) findViewById(R.id.bt_submit);
    }

    public void writeNewUser() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        database.getReference().child("USERS").child("1");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_submit:
//                writeNewUser();
                break;
        }
    }
}