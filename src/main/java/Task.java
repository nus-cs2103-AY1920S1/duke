/**
 * Tasks include events, deadlines and todo actions.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Tasks have a description and are initialised as undone.
     *
     * @param description An activity that the user should attend/complete.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Formats the task to be stored in the hard disk.
     *
     * @return Formatted task with its details.
     */
    public String toSave() {
        return "";
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the status of the task as done or undone.
     *
     * @return 1 or 0 if done or undone respectively.
     */
    public String getBinaryStatus() {
        return (isDone ? "1" : "0");
    }

    /**
     * Gets the status of the task as done or undone.
     *
     * @return v or x if done or undone respectively.
     */
    public String getStatusIcon() {
        return (isDone ? "v" : "x"); //return tick or X symbols
    }

}