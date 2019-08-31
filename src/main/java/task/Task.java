package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected boolean deleted;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.deleted = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public abstract String toString();
}
