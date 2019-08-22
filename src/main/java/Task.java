public class Task {
    private boolean isDone;
    protected String description;

    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public void mark() {
        if (!isDone)
            this.isDone = true;
    } //update status of task

    protected String getStatusIcon() {
        return (isDone ? "[\u2713] " : "[\u2718] "); //return tick or X symbols
    }

    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}