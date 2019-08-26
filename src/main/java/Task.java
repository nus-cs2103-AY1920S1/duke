/**
 * Represents a task in a Duke object's task list
 * Each task has a description and marker to indicate if it is completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task object.
     * Each task is set as not done by default.
     *
     * @param description The string description of the task created.
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the String unicode of the tick symbol if the task is marked done,
     * and returns the String unicode of the cross symbol if otherwise.
     *
     * @return the String unicode of either the tick or cross symbol.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Sets the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Returns the String format of the task.
     * The String format of a task consists a tick or cross symbol to indicate if it is done
     * and its description.
     *
     * @return String format of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + this.description;
    }
}
