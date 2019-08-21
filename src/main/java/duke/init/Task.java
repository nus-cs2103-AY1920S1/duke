package duke.init;

/**
 * @author lyskevin
 */
public class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructs a task with the specified description.
     * @param description The specified description.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Returns true if this task is done.
     * @return true if this task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets this task as done.
     */
    public void setAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the string representation of this task.
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        String statusIcon;
        if (isDone) {
            statusIcon = "\u2713";
        } else {
            statusIcon = "\u2718";
        }
        return String.format("[%s] %s", statusIcon, description);
    }

}
