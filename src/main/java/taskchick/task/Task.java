package taskchick.task;

/**
 * Tasks include events, deadlines and todo actions.
 */
public class Task {

    protected String description;
    protected boolean isCompleted;

    /**
     * Creates a task that is initialised as incomplete.
     *
     * @param description An activity that the user should attend/complete.
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the description of the task.
     *
     * @param newDescription New description to replace the existing one.
     */
    public void updateDescription(String newDescription) {
        description = newDescription;
    }

    /**
     * Retrieves the date assigned to the task (not applicable to todo types).
     *
     * @return A dummy value - this method will be overridden in Deadline and Event classes.
     */
    public int[] getDate() {
        return null;
    }

    /**
     * Marks a task as completed.
     */
    public void markAsCompleted() {
        this.isCompleted = true;
    }

    /**
     * Gets the status of the task as done or undone.
     *
     * @return v or x if done or undone respectively.
     */
    public String getStatusIcon() {
        return (isCompleted ? "v" : "x");
    }

    /**
     * Formats the task to be stored in the hard disk, which differs based on the task type.
     *
     * @return Formatted task with its details.
     */
    public String toSave() {
        return "";
    }
}