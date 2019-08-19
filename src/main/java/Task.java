public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "O" : "X");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDesc() {
        return this.description;
    }
}