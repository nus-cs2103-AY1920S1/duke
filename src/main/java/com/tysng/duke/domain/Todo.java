package com.tysng.duke.domain;

/**
 * This generic Todo class extends from the base Task class.
 */
public class Todo extends Task {
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}

