package duke.task;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this(description, false);
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "O" : "X"); //return O or X symbols
    }

    protected boolean markAsDone() {
        this.isDone = true;
        return isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return '[' + getStatusIcon() + ']' + ' ' + description;
    }
}
