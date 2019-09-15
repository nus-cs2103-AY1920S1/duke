package com.tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description, null);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String getTaskType() {
        return "T";
    }
}
