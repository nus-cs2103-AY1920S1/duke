public class Task {
    protected boolean isDone;
    protected String description;
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone? "\u2713" : "\u2718");
    }
}
