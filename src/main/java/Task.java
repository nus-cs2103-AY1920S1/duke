public abstract class Task {
    private String description;
    private boolean isDone;
    private String icon;

    Task(String icon, String description) {
        this.icon = icon;
        this.description = description;
    }

    void markAsDone() {
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
        return String.format("%s|%d|%s", icon, isDone ? 1 : 0, getDescription());
    }

    @Override
    public String toString() {
        return String.format("  [%s][%s]%s", getStatusIcon(), getDescription());
    }
}
