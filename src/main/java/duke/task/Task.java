package duke.task;

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

    public String getDescription() {
        return description;
    }

    public void updateDescription(String newDescription) {
        description = newDescription;
    }

    /**
     * Formats the task to be stored in the hard disk.
     *
     * @return Formatted task with its details.
     */
    public String toSave() {
        return "";
    }

    public int[] getDate() {
        return new int[]{0};
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
        return (isCompleted ? "v" : "x"); //return tick or X symbols
    }

}