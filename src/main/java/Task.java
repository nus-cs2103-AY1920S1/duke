/**
 * Represent Tasks and its given description.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a new Task with the given description.
     * @param description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Indicates whether the task is done or not.
     * @return true or false
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Mark this task as done and provide notification.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + this.toString());
    }

    /**
     * Mark this task as done without notification.
     */
    public void quietMarkAsDone() {
        this.isDone = true;
    }

    /**
     * Convert done state of this Task to a symbol.
     * @return + if task is completed.
     */
    public String getStatusIcon() {
        return (isDone ? "+" : " "); //return "+" if done " " otherwise
    }

    /**
     * Get the description of this task.
     * @return description of this task.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return ("[" + getStatusIcon() + "] " + this.description);
    }
}