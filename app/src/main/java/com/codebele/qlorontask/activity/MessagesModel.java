package com.codebele.qlorontask.activity;

public class MessagesModel {
    String comments, replay;
    String like;
public MessagesModel(){}
    public MessagesModel(String comments, String replay, String like) {
        this.comments = comments;
        this.replay = replay;
        this.like = like;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getReplay() {
        return replay;
    }

    public void setReplay(String replay) {
        this.replay = replay;
    }
}
