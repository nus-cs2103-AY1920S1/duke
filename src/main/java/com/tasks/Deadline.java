package com.tasks;

import com.util.json.JsonObject;
import com.util.json.Schema;

public class Deadline extends DoableTask {

    /**
     * The task's deadline string.
     */
    private String deadline;

    /**
     * Create DoableTask of this type with deadline string.
     *
     * @param taskName name of task
     * @param due      deadline string
     */
    public Deadline(String taskName, String due) {
        super(taskName);
        deadline = due;
    }

    /**
     * get string representation of task.
     *
     * @return string
     */
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }

    @Override
    public JsonObject toJson() {
        return new JsonObject().put(Schema.TASK_DEADLINE, super.toJson()
                .put(Schema.ATTR_DEADLINE_DUE, deadline.replaceAll("\"", "\\\\\"")));
    }
}