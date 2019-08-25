package duke.task;

/**
 * Represents Tasks.
 * Tasks could be ToDos, Deadlines, Events.
 */
public class Task {
    /** Total tasks created. */
    private static int totalTasks = 0;
    /** Whether task is done. */
    private boolean isDone;
    /** String of the task. */
    private String description;

    /**
     * Creates an instance of Task.
     *
     * @param description String of the task.
     */
    public Task(String description) {
        this.description = description;
        ++totalTasks;
    }

    /**
     * Gets the description of the task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the total number of tasks created.
     *
     * @return The total tasks created.
     */
    public static int getTotalTasks() {
        return totalTasks;
    }

    /**
     * Changes isDone to true, setting the task to be done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets the string message of the task.
     *
     * @return String message.
     */
    public String message() {
        String indent = String.format("%4s", "");
        return new StringBuilder()
                .append(indent + " Got it. I've added this task:\n")
                .append(indent + "   " + this + "\n")
                .append(indent + " Now you have " + totalTasks + " tasks in the list.")
                .toString();
    }

    /**
     * Checks whether the task is done.
     *
     * @return Boolean value on whether task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the string symbol of whether the task is done.
     *
     * @return String symbol of whether the task is done.
     */
    private String getStatusIcon() {
        return isDone ? "✓" : "✗";
    }

    /**
     * Returns String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
