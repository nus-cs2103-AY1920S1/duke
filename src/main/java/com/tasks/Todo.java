package com.tasks;

import com.util.json.JsonObject;

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

    @Override
    public JsonObject toJson() {
        return new JsonObject().put("todo", super.toJson());
    }
}