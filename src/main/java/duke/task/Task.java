package duke.task;

/**
 * Represents a task from the user's to do list.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task.
     * @param description the description of the task
     */
    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the tick/cross symbol according to isDone status.
     * @return tick or cross symbols
     */
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Converts the task into a StringBuilder object to be written into storage.
     *
     * @return StringBuilder object for file writing
     */
    public StringBuilder saveData() {
        if (isDone) {
            return new StringBuilder("1|").append(description);
        }
        return new StringBuilder("0|").append(description);
    }

    /**
     * Returns task description.
     *
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of this task.
     *
     * @return a string representation of this task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}