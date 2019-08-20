package main.java;

abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this(description, false);
    }

    private Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "O" : "X"); //return O or X symbols
    }

    protected boolean markAsDone() {
        this.isDone = true;
        return isDone;
    }

    @Override
    public String toString() {
        return '[' + getStatusIcon() + ']' + ' ' + description;
    }
}
