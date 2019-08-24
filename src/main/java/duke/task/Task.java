package duke.task;

public abstract class Task {

    final String description;
    boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "✓" : "✘");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String toSaveString();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
