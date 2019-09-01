package seedu.duke.task;

public abstract class Task {

    private String taskName;
    private boolean isDone;

    public Task(String task) {
        this.taskName = task;
        this.isDone = false;
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.taskName);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String toStorageString();

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

}
