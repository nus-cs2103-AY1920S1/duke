package task;

/**
 * Used for testing purposes.
 */
public class TaskStub extends Task {
    public TaskStub(String description) {
        super(description);
        super.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return description;
    }

    public String parse() {
        return description;
    }
}
