package com.leeyiyuan.task;

public class Task {

    protected String title;
    protected boolean isDone;

    public Task() {

    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
