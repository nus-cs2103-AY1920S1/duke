public class Task {
    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
    }

    void markAsDone() {
        isDone = true;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    private String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
