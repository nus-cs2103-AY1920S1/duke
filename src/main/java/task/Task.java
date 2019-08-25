package task;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, int isDone) {
        this.description = description;
        this.isDone = isDone == 1;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        // return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
        return isDone ? "O" : "X";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}