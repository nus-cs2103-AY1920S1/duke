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

    protected String getDoneStatus() {
        return (isDone ? "1" : "0");
    }

    public Task markAsDone() {
        isDone = true;
        return this;
    }

    public String toWriteFile() {
        return "T | " + getDoneStatus() + " | " + description;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
