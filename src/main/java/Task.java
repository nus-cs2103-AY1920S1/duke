/**
 * Represents a task that user adds to their daily task list
 */

public class Task {
    private String name;
    private boolean isDone;

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public boolean hasDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0"); //return tick or X symbols
    }

    public String getName() {
        return name;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
