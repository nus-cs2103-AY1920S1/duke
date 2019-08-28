public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void done() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String toStringFile() {
        return ((isDone) ? "1" : "0") + " | " + description;
    }
}
