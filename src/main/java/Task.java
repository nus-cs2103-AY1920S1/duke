public class Task {
    protected boolean isDone = false;
    protected String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]");
    }

    public void complete() {
        this.isDone = true;
    }

    public String toString() {
        return this.getStatusIcon() + this.taskName;
    }
}