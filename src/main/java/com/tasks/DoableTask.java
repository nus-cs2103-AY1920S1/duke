package com.tasks;

public class DoableTask extends Task {

    /**
     * Flag denoting task is done.
     */
    private boolean isDone;

    /**
     * Create instance of Task with isDone flag.
     *
     * @param taskName name of task
     */
    public DoableTask(String taskName) {
        super(taskName);
        isDone = false;
    }

    /**
     * Set isDone to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Get icon based on isDone status.
     *
     * @return icon string
     */
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * get string representation of task.
     *
     * @return string
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + super.toString();
    }
}
