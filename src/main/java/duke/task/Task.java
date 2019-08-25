package duke.task;

public class Task {
    String description;
    boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getOutputFormat() {
        return String.format("X | %d | %s", isDone ? 1 : 0, description);
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}