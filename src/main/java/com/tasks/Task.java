package com.tasks;

public class Task {

    /**
     * Name of task.
     */
    private String name;

    /**
     * Create a task.
     *
     * @param taskName name of task.
     */
    public Task(String taskName) {
        name = taskName;
    }

    /**
     * get string representation of task.
     *
     * @return string
     */
    public String toString() {
        return name;
    }
}
