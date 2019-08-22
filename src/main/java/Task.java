public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        // Return tick or X symbols
        return (isDone ? "\u2713" : "\u2718");
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
