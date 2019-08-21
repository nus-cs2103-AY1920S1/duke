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
        return (isDone ? "[\u2713] " + this.description : "[\u2718] " + this.description); //return tick or X symbols
    }
}
