public class Task {
    protected boolean isDone;
    protected String description;

    Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }
}