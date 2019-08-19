public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public Task markAsDone() {
        isDone = true;
        return this;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
