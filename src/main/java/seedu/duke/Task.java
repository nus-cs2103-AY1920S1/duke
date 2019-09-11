package seedu.duke;

/**
 * Base class used for Task objects.
 */
public abstract class Task {
    /** String representing the title of the Task.*/
    protected String description;
    /** Boolean representing whether the task has been done.*/
    protected boolean isDone;
    /** String representing type of Task. */
    protected String type = null;

    /** Constructor.*/
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns Task description.
     * @return Task description.
     */
    public String getFullDescription() {
        return description;
    }

    /**
     * Base method for returning Task type.
     * @return null Task type.
     */
    public String getType() {
        return "null";
    }

    /**
     * Returns String representing done status of Task.
     * @return String representing done status of Task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Sets isDone to true.
     */
    public void done() {
        isDone = true;
    }

    /**
     * Establishes String representation of object.
     * @return String representing the object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract void editTask(String description, String details);
}
