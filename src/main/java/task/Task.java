package task;

public class Task {
    protected String description;
    protected Boolean isDone;

    /**
     * Constructor for a general Task object.
     * @param description String description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task. Returns "+" if the task is done.
     * Else it returns "-".
     * @return String status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "+" : "-"); //return tick or X symbols
    }

    /**
     * Marks the given task as done.
     * @param task Task to be marked as done.
     */
    public static void markAsDone(Task task) {
        task.isDone = true;
    }

    /**
     * toString() returns the string representation of this task for user interface to print.
     * @return String.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * toDataFormat() returns the string representation of this task for data storage.
     * @return
     */
    public String toDataFormat() {
        return "T | " + this.getStatusIcon() + " | " + this.description;
    }

    public boolean hasDescription(String desc) {
        return this.description.contains(desc);
    }
}
