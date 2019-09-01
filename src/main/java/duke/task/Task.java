package duke.task;

/**
 * Task class, for inheritance of various tasks.
 */
public class Task {
    private String description;
    private boolean isDone = false;

    /**
     * Constructs a Task object.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns status icon of the task.
     *
     * @return the tick icon [✓] if task is done, or cross icon [✗] otherwise.
     */
    private String getStatusIcon() {
        if (this.isDone) {
            return "[✓] ";
        } else {
            return "[✗] ";
        }
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Extracts description of the task.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gives a string representation of the task for storage.
     *
     * @return a string representation of the task for storage.
     */
    public String toDataString() {
        return (this.isDone ? 1 : 0) + " | " + this.description;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}
