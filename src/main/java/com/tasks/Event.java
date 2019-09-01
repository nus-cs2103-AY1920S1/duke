package com.tasks;

import com.util.json.JsonObject;

public class Event extends DoableTask {

    /**
     * The task's dateRange string.
     */
    private String dateRange;

    /**
     * Create DoableTask of this type with dateRange string.
     *
     * @param taskName name of task
     * @param range    dateRange string
     */
    public Event(String taskName, String range) {
        super(taskName);
        dateRange = range;
    }

    /**
     * get string representation of task.
     *
     * @return string
     */
    public String toString() {
        return "[E]" + super.toString() + "(at: " + dateRange + ")";
    }

    @Override
    public JsonObject toJson() {
        return new JsonObject().put("event", super.toJson()
                .put("start", dateRange.replaceAll("\"", "\\\\\"")).put("end", ""));
    }
}