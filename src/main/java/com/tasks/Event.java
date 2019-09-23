package com.tasks;

import com.util.json.JsonObject;
import com.core.savedata.Schema;
import com.util.datetime.DateTime;

public class Event extends DoableTask {

    /**
     * The task's start range.
     */
    public DateTime startDate;

    /**
     * The task's end range.
     */
    public DateTime endDate;

    /**
     * Create DoableTask of this type with dateRange string.
     *
     * @param taskName  name of task
     * @param start     start of date range
     * @param end       end of date range
     */
    public Event(String taskName, DateTime start, DateTime end) {
        super(taskName);
        startDate = start;
        endDate = end;
    }

    /**
     * get string representation of task.
     *
     * @return string
     */
    public String toString() {
        return "[E]" + super.toString() + "\n (at: " + startDate.toString() + " to " + endDate.toString() +  ")";
    }

    @Override
    public JsonObject toJson() {
        return new JsonObject().put(Schema.TASK_EVENT, super.toJson()
                .put(Schema.ATTR_EVENT_START, startDate.toString())
                .put(Schema.ATTR_EVENT_END, endDate.toString()));
    }
}