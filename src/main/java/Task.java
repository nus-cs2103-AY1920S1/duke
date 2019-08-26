public class Task {
    protected boolean isDone;
    protected String description;

    Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    protected String getDescription() {
        return description;
    }

    protected void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}