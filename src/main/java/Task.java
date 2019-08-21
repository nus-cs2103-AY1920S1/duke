/**
 * Task class. Holds the string of the task, and whether it is done.
 */
public class Task {
    /** Total tasks created. */
    private static int totalTasks = 0;
    /** Whether task is done. */
    private boolean isDone;
    /** String of the task. */
    private String description;

    /**
     * Constructor.
     * @param description String of the task.
     */
    public Task(String description) {
        this.description = description;
        ++totalTasks;
    }

    /**
     * Gets the total number of tasks created.
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
     * Gets the string symbol of whether the task is done.
     * @return String symbol of whether the task is done.
     */
    private String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    /**
     * String representation of the task.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
