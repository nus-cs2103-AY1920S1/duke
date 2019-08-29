package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected int num;

    /**
     * Represents a task.
     */

    public Task() {
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.num = 0;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setStatusIcon(boolean b) {
        isDone = b;
        if (isDone) {
            num = 1;
        }
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
