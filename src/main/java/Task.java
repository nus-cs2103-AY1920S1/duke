public class Task {
    protected boolean isDone = false;
    protected String description;

    public Task(String description) {
        this.description = description;
    }

    public String getStatusIcon() {
        return (isDone ? "[1]" : "[0]");
    }

    public void complete() {
        this.isDone = true;
    }

    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}