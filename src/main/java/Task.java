public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        String statusIcon = isDone ? "\u2713" : "\u2718";
        return "[" + statusIcon + "] " + this.description;
    }
}
