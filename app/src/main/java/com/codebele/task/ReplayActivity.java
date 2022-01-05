package com.codebele.task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReplayActivity extends AppCompatActivity implements View.OnClickListener {
    ReplayAdapter replayAdapter;
    RecyclerView rvReplay;
    ArrayList<ReplyModel> commentList = new ArrayList<>();
    FirebaseDatabase database;
    String replayText;
    TextView tvReplay;
    ImageView ivOnBackPress,ivSend;
    EditText etReplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay);
        database = FirebaseDatabase.getInstance();
        rvReplay = (RecyclerView)findViewById(R.id.rv_replay);
        tvReplay = (TextView)findViewById(R.id.tv_tool);
        etReplay = (EditText) findViewById(R.id.et_comment);
        ivSend = (ImageView) findViewById(R.id.iv_send);
        Bundle extras = getIntent().getExtras();
        replayText = extras.getString("comm");
        tvReplay.setText(replayText);
        firestorevalue();
        comentRecy();
        ivOnBackPress = (ImageView)findViewById(R.id.iv_onBackPress);
        ivOnBackPress.setOnClickListener(this);
        ivSend.setOnClickListener(this);
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                if (source.length()>0 &&Character.isWhitespace(source.charAt(0))
                        && etReplay.getText().toString().length()==0) {
                    return "";
                }
                return source;
            }
        };
        etReplay.setFilters(new InputFilter[]{filter});
    }
    private void comentRecy(){
        replayAdapter = new ReplayAdapter(getApplicationContext(),commentList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvReplay.setLayoutManager(layoutManager);
        rvReplay.setAdapter(replayAdapter);
    }
    private void replayText(){
        //            commentList.add(new CommentModle("",etComment.getText().toString()));
        ReplyModel messagesModel = new ReplyModel(etReplay.getText().toString());
        database.getReference().child("COMMENTES").child("Video ID 1").child(replayText)
                .push()
                .setValue(messagesModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

    }
    private void firestorevalue() {
        DatabaseReference chatref = database.getReference().child("COMMENTES").child("Video ID 1").child(replayText);
        chatref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                commentList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ReplyModel messsages = dataSnapshot.getValue(ReplyModel.class);
                    commentList.add(messsages);
                    Log.d("listmounesh", String.valueOf(commentList));
                    comentRecy();
                    replayAdapter.notifyDataSetChanged();
                    rvReplay.scrollToPosition(replayAdapter.getItemCount() - 1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_onBackPress:
                onBackPressed();
                break;
            case R.id.iv_send:
                if (!etReplay.getText().toString().isEmpty()) {
                    replayText();
                    etReplay.setText("");
                }

                break;
        }
    }
}