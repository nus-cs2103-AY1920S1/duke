package duke.task;

/**
 * Represents a general task.
 */
public class Task {
    String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    public void setDone() {
        isDone = true;
    }

    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    int getStatus() {
        return (isDone ? 1 : 0);
    }

    public String store() {
        return getStatus() + "|" + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}