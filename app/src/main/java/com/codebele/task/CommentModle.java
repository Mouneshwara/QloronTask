package com.codebele.task;

public class CommentModle {
    String comment,replay;

    public CommentModle(String comment, String replay) {
        this.comment = comment;
        this.replay = replay;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReplay() {
        return replay;
    }

    public void setReplay(String replay) {
        this.replay = replay;
    }
}
