public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public final String getStatusIcon() {
        return (isDone ? "O" : "X");
    }

    public final void markAsDone() {
        this.isDone = true;
    }

    public final String getDesc() {
        return this.description;
    }

    @Override
    public abstract String toString();
}