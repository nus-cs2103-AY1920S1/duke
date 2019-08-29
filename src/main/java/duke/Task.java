package duke;

/**
 * The Task class contains methods for query of the tasks
 * as well as to set tasks as done.
 */
public class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructor for Task class.
     *
     * @param description The task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns tick or cross symbols to signify if a task is done.
     *
     * @return symbol A tick or cross symbol.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns an int to indicate if the task is done.
     *
     * @return int 1 is used to indicate a task as done, 0 otherwise.
     */
    public int isDone() {
        return isDone ? 1 : 0;
    }

    /**
     * Sets the task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Overrides the toString method to print the task.
     *
     * @return String Task formatted to string including status icon.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] "+ this.getDescription();
    }
}