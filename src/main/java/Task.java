public class Task {
    protected String taskName;
    protected boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void setDone(boolean status) {
        this.isDone = status;
    }

    @Override
    public String toString() {
        return (isDone ? "[\u2713] " : "[\u2718] ") + this.taskName;
    }
}
