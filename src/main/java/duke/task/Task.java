package duke.task;

public abstract class Task {

    protected boolean isDone;
    protected String description;

    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    public Task(String description, boolean isDone) {
        this.isDone = isDone;
        this.description = description;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    abstract public String getFileStringFormat();
}
