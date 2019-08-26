package duke.task;

/**
 * Create a Task.
 */
public class Task {
    String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "[Y]" : "[N]");
    }

    String getDoneStatus() {
        return (isDone ? "1" : "0");
    }

    public Task markAsDone() {
        isDone = true;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public String toWriteFile() {
        return "T | " + getDoneStatus() + " | " + description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
