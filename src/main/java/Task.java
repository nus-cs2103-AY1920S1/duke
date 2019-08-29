/**
 * Represents a task.
 */
public class Task {

    /**
     * Description of the task.
     */
    protected String description;

    /**
     * The status of the task.
     */
    protected boolean isDone;

    /**
     * Done status of the task.
     */
    protected int status;


    /**
     * Constructs a task.
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.status = 0;
    }

    /**
     * Gets status icon.
     * @return status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    //...

    /**
     * Marks task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
        this.status = 1;
    }

    /**
     * Returns the task and state.
     * @return task and state
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Gets status of the task.
     * @return
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Gets description of the task.
     * @return description of the task
     */
    public String getDescription() {
        return description;
    }
}