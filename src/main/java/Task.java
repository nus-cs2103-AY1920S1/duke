public class Task {
    protected boolean isDone = false;
    protected String description;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public void complete() {
        this.isDone = true;
    }

    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}