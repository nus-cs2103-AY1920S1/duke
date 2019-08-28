package duke.models;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean contains(String txt) {
        return description.contains(txt);
    }

    public abstract String getData();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}