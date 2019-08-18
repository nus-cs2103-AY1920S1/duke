/** A class that represents a task. */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task Object.
     * @param description task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon showing the status of the task.
     * @return tick icon if the task is done otherwise X icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Set the status of the task to be done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Returns a String representation of the task.
     * @return a String representation of the task, consisting of status icon
     *         and description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
