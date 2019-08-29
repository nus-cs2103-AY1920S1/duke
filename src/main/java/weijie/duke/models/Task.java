package weijie.duke.models;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return getTaskIcon() + "[" + getStatusIcon() + "] " + description + " " + getDateTime();
    }

    private String getStatusIcon() {
        return isDone ? "✓" : "✗";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String getDateTime();

    public abstract String getTaskIcon();
}
