package com.leeyiyuan.task;

/**
 * Represents a todo task.
 */
public class TodoTask extends Task {

    /**
     * Constructs a TodoTask.
     */
    public TodoTask() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone ? "✓" : "✗", this.title);
    }
}
