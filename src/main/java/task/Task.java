package task;

/**
 * Defines the general structure of tasks.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs the Deadline object with specified completion status.
     * @param description Task description.
     * @param isDone Task completion status.
     */
    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructs the object with default completion status of FALSE.
     * @param description Task description.
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Converts completion status of task into symbols.
     * @return Notation of status completion.
     */
    String getStatusIcon() {
        return (isDone ? "+" : "-");
    }

    /**
     * Sets the completion status of task to TRUE.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Returns the literal description of the object.
     * @return Understandable description of object.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Formats the object so that it can be save into file.
     * @return Formatted description of the object.
     */
    public String toFile() {
        return "";
    }

    /**
     * Returns the specific task's description.
     * @return Task's description.
     */
    String getDescription() {
        return description;
    }
}