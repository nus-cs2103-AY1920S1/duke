package seedu.duke;

/**
 * Represents a task. A <code>Task</code> object
 * can take in a description.
 */
public class Task {
    protected boolean isDone;
    protected String description;

    /**
     * Class constructor.
     * Marks task is automatically as undone.
     *
     * @param description String description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return String description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the status icon of cross or tick of the task.
     * Tick for a task that is done and cross for a undone task.
     *
     * @return String status icon of task.
     */
    public String getStatusIcon() {
        return (isDone? "\u2713" : "\u2718");
    }

    /**
     * Retrieves the 1 or 0 of the task.
     * 1 for a task that is done and 0 for a undone task.
     *
     * @return String 1 or 0 of task.
     */
    public String getIfDone() {
        return (isDone? "1" : "0");
    }

    /**
     * String representation of the task.
     *
     * @return String representation of task as [status icon] task description.
     */
    public String toString() {
        String msg = "[" + this.getStatusIcon() + "] " + this.getDescription();
        return msg;
    }

    /**
     * Returns string representation of the task to be written into data file.
     *
     * @return String representation of task as | 1 or 0 | task description.
     */
    public String toWriteIntoFile() {
        String msg = " | " + this.getIfDone() + " | " + this.getDescription();
        return msg;
    }
}
