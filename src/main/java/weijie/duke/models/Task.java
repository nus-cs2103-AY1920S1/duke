package weijie.duke.models;

public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return getTaskIcon() + "[" + getStatusIcon() + "] " + description + " " + getDateTimeInfo();
    }

    private String getStatusIcon() {
        return isDone ? "✓" : "✗";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String getDateTimeInfo();

    public abstract String getTaskIcon();
}
