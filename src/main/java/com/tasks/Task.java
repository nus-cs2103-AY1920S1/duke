package com.tasks;

import com.util.json.JsonObject;

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

    protected String toJsonContent() {
        return "\"name\": \"" + name + "\"";
    }

    public JsonObject toJson() {
        JsonObject obj = new JsonObject();
        obj.put("name", name.replaceAll("\"", "\\\\\""));
        return obj;
    }
}
