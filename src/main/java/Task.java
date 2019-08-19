public abstract class Task {
    private String description;
    private boolean isDone;

    Task(String description) {
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

    @Override
    public abstract String toString();
}
