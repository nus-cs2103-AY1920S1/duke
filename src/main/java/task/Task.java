package task;

public class Task {
    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    String getStatusIcon() {
        return (isDone ? "+" : "-");
    }

    public void markDone() {
        isDone = true;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toFile() {
        return "";
    }

    public String getDescription() {
        return description;
    }
}