package duke.task;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    protected String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public abstract String format();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}

