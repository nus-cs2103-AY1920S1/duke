package duke.task;

/**
 * Represents a task that user adds to their daily task list
 */

public class Task {
    private String name;
    private boolean isDone;
    private String type;

    public Task(String name, boolean isDone, String type) {
        this.name = name;
        this.isDone = isDone;
        this.type = type;
    }

    public boolean hasDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0");
    }

    public String getName() {
        return name;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getType() {
        return type;
    }
}
