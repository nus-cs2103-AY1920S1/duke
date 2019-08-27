package duke.task;

public abstract class Task {
    private String description;
    private boolean isDone;
    private String icon;

    Task(String icon, String description) {
        this.icon = icon.trim();
        this.description = description.trim();
    }

    public void markAsDone() {
        isDone = true;
    }

    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    String getDescription() {
        return this.description;
    }

    String getIcon() {
        return this.icon;
    }

    public String toSaveFormat() {
        return String.format("%s | %d | %s", getIcon(), isDone ? 1 : 0, getDescription());
    }

    @Override
    public String toString() {
        return String.format("  [%s][%s] %s", getIcon(), getStatusIcon(), getDescription());
    }
}
