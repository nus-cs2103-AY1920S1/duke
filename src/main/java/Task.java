public class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    void markAsDone() {
        isDone = true;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
