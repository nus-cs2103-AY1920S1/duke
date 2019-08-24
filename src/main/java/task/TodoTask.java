package com.leeyiyuan.task;

public class TodoTask extends Task {

    public TodoTask() {
        super();
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone ? "✓" : "✗", this.title);
    }
}
