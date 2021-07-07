/**
 * Represents the 3 Tasks of Todo, Deadline and Storage.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task class.
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Returns if task object is done or not.
     * @return true if task is done false otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns the tick or cross symbol to represent task is done or not respectively.
     * @return a tick or cross
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns a string representation with the status icon(tick or cross) and description.
     * @return string representation of task icon and description.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.getDescription();
    }

    /**
     * Returns a string representation of description.
     * @return a string representation of description.
     */
    public String getDescription() {
        return description;
    }
}
