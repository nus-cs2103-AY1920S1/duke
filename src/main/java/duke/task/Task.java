package duke.task;

import java.text.ParseException;

public class Task {
    private String description;
    private boolean isDone;
    protected String type;
    protected String event;

    /**
     * Creates a task object.
     * Called by task, deadline and event.
     *
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status of task.
     *
     * @return tick or X symbols to represent whether task is done.
     */
    public String getStatusIcon() {
        return isDone ? "✓" : "✗";
    }

    /**
     * Returns description of task.
     *
     * @return description of task
     */
    public String getDesc() {
        return this.description;
    }

    public String getAt() {
        return "";
    }

    public String getBy() {
        return "";
    }

    /**
     * Returns whether task is done in the form of a string.
     *
     * @return status of task
     */
    public String getDone() {
        return this.isDone ? "1" : "0";
    }

    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Represents the task in a format suitable for the user to read.
     *
     * @return string representation of task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}