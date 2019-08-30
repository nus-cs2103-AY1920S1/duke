package duke.bin.task;

/**
 * Abstract class for task.
 */
public abstract class Task {
    protected boolean isDone;
    protected String name;

    /**
     * Constructor for task.
     *
     * @param name name of the task.
     */
    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    /**
     * Returns the name of the given task.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the status icon: tick or X symbols.
     *
     * @return the String format of the icon.
     */
    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718"; //return tick or X symbols
    }

    /**
     * Marks this task as completed.
     *
     * @return this task.
     */
    public Task completed() {
        isDone = true;
        return this;
    }

    /**
     * Returns the type of task it is attached to.
     *
     * @return a String representation of type.
     */
    public abstract String getType();

    /**
     * Returns the string representation of this task.
     *
     * @return format of string for this task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + name;
    }
}