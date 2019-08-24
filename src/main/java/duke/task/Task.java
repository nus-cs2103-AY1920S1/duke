package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    public abstract String getTaskInitial();

    /**
     * Override this function to provide extra text at the end of the duke.task's string representation.
     * @return Extra text to be inserted at the end of the duke.task's string representation
     */
    protected String extraText() {
        return "";
    }

    /**
     * Override this function to provide extra text at the end of the duke.task's save string representation.
     * @return Extra text to be inserted at the end of the duke.task's save string representation
     */
    protected String extraSaveText() {
        return "";
    }

    public String toSaveString() {
        return this.getTaskInitial() + " | " + (this.isDone ? "1" : "0") + " | " + this.description
             + this.extraSaveText();
    }

    @Override
    public String toString() {
        return "[" + this.getTaskInitial() + "][" + this.getStatusIcon() + "] " + this.description
             + this.extraText();
    }
}
