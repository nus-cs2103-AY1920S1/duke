package duke.task;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected int num;

    public Task() {
    }

    /**
     * Constructs a task.
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.num = 0;
    }

    public abstract int getDiffDays();

    /**
     * To get the status icon based on the status.
     * @return tick or X symbols.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Updates the status icon.
     * @param b the new status.
     */
    public void setStatusIcon(boolean b) {
        isDone = b;
        if (isDone) {
            num = 1;
        }
    }

    public String getDesc() {
        return description;
    }

    /**
     * Deals with changing the task to file format string.
     *
     * @return task as string.
     */
    public String format() {
        return "|" + num + "|" + description;
    }

    /**
     * Deals with changing the task to print format string.
     *
     * @return task as string.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
