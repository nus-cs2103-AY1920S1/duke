package duke.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() { return isDone; }

    public void markDone() {
        this.isDone = true;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", getStatusIcon(), description);
    }

    public String formatString() {
        return String.format(
                "%d|%s", isDone ? 1 : 0, description
        );
    }
}