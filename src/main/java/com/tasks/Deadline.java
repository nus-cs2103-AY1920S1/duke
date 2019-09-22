package com.tasks;

import com.util.json.JsonObject;
import com.core.savedata.Schema;
import com.util.datetime.DateTime;

public class Deadline extends DoableTask {

    /**
     * The task's deadline datetime.
     */
    public DateTime deadline;

    /**
     * Create DoableTask of this type with deadline string.
     *
     * @param taskName name of task
     * @param cal      deadline string
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

    @Override
    public JsonObject toJson() {
        return new JsonObject().put(Schema.TASK_DEADLINE, super.toJson()
                .put(Schema.ATTR_DEADLINE_DUE, deadline.toString()));
    }
}