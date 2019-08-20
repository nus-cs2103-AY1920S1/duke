public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescriptionWithStatus() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}