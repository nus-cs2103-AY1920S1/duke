public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "x" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}