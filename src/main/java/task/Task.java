package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    public abstract String getTaskInitial();

    public abstract boolean isValid();

    // Override this function to provide extra text at the end of the task's string representation
    public String extraText() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + this.getTaskInitial() + "][" + this.getStatusIcon() + "] " + this.description + this.extraText();
    }
}
