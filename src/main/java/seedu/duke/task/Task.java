package seedu.duke.task;

import seedu.duke.priority.Priority;

public class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u274C");
    }

    /** Set a task as done and returns an acknowledgement of making the task as done.
     * @return a string to acknowledge successful marking of task as done.
     */
    public String markAsDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done:\n\t" +  " [\u2713] " + description;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String writeToFile() {
        return "";
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
