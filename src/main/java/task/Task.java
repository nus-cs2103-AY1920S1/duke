package task;

/**
 * Handles task types.
 * */
public class Task {
    protected String description;
    protected boolean isDone;

    // initialize new task
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getType() {
        return "GENERAL";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
