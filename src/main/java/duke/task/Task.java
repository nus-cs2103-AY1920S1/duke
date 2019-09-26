package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task object.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the current status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks the task as done.
     */
    public void done() {
        isDone = true;
    }

    /**
     * Prepares the task to be saved to flat file format.
     */
    public String printSave() {
        return "T | " + ((isDone) ? "1" : "0") + " | " + description;
    }

    /**
     * Prepares the task to be output on the GUI.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }
}
