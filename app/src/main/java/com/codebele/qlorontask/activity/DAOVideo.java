package com.codebele.qlorontask.activity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOVideo {
    private DatabaseReference databaseReference;
    public DAOVideo(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(MessagesModel.class.getSimpleName());
    }

    public Task<Void> add(MessagesModel messagesModel){
     return  databaseReference.push().setValue(messagesModel);
    }
}
