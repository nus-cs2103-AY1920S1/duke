package duke.task;

import java.util.Date;

public abstract class Task {

    final String description;
    boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String toEncodedString();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    private String getStatusIcon() {
        return (isDone ? "✓" : "✘");
    }

}
