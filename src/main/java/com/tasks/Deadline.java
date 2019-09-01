package com.tasks;

import com.util.datetime.DateTime;

public class Deadline extends DoableTask {

    /**
     * The task's deadline datetime.
     */
    private DateTime deadline;

    /**
     * Create DoableTask of this type with deadline string.
     *
     * @param taskName name of task
     * @param due      deadline string
     */
    public Deadline(String taskName, DateTime cal) {
        super(taskName);
        deadline = cal;
    }

    /**
     * get string representation of task.
     *
     * @return string
     */
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline.toString() + ")";
    }
}