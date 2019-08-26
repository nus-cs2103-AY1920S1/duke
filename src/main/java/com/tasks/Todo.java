package com.tasks;

public class Todo extends DoableTask {

    /**
     * Create DoableTask of this type.
     *
     * @param taskName name of task
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * get string representation of task.
     *
     * @return string
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}