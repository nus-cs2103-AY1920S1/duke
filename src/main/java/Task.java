public class Task {
    private String description;
    private boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}