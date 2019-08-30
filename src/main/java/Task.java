/**
 * Represents a task.
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class Task {
    protected static int total;
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task with description.
     * @param description Description of task.
     */
    public Task(String description) {
        total++;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the total number of tasks currently.
     * @return The total number of tasks currently
     */
    public static int getTotal() {
        return total;
    }

    protected static void decTotal() {
        total--;
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns if the task is done.
     * @return If the task is done.
     */
    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Sets the task to be done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns the appropriate icon depending on the completed status of the task
     * @return The appropriate icon depending on the completed status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    /**
     * Returns a string representation of the task object for UI.
     * @return A string representation of the task object for UI.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }

    /**
     * Returns a string representation of the task object for Storage.
     * @return A string representation of the task object for Storage.
     */
    public String saveString() {
        int done = this.isDone ? 1 : 0;
        return " | " + done + " | " + this.description;
    }
}
