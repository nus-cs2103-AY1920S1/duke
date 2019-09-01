package com.tasks;

import com.util.json.JsonObject;
import com.util.json.Schema;

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

    /**
     * Returns a JsonObject representation of this task.
     * @return  JsonObject
     */
    public JsonObject toJson() {
        JsonObject obj = new JsonObject();
        obj.put(Schema.ATTR_NAME, name.replaceAll("\"", "\\\\\""));
        return obj;
    }
}
