public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task Constructor.
     * @param description is the task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713"
                        : "\u2718"); //return tick or X symbols
    }

    public String toDataFormat() {
        return (isDone ? "1"
                    : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
