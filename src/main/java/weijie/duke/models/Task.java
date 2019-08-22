package weijie.duke.models;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return "[" + getStatusIcon() + "] " + description;
    }

    private String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
