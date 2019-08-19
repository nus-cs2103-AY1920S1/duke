public class Task {
    protected boolean isDone = false;
    protected String description;
    
    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "\u2713" : "\u2718";
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
