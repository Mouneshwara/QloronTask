package com.codebele.task;

public class MessagesModel {
    String comments, reply;
public MessagesModel(){}

    public MessagesModel(String comments) {
        this.comments = comments;

    }
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


}
