package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        isDone = false;
    }

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "Y" : "N");
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String toFile() {
        return "T|" + getStatusIcon() + "|" + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
