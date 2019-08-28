package duke.task;

/**
 * Task class. Provides a framework for new Task objects.
 */
public abstract class Task {

    /** Stores the status of the task. */
    Status completed;
    /** Stores the name of the task. */
    String taskName;

    /**
     * Creates a new task object.
     * @param taskName The name of the task.
     */
    Task(String taskName) {
        this.completed = Status.INCOMPLETE;
        this.taskName = taskName;
    }

    /**
     * Creates a new task object with the status predefined.
     * @param status The status of the task.
     * @param taskName The name of the task.
     */
    Task(Status status, String taskName) {
        this.completed = status;
        this.taskName = taskName;
    }

    /**
     * Marks the task as completed.
     * @return Returns a boolean describing if the task has successfully been marked done.
     */
    public boolean markAsComplete() {
        if (this.completed == Status.INCOMPLETE) {
            completed = Status.COMPLETE;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a string describing the task.
     * @return A string describing the task.
     */
    @Override
    public String toString() {
        return "[" + (this.completed == Status.COMPLETE ? "✓" : "✗") + "] " + taskName;
    }

    /**
     * Returns a string in the format to be saved to disk.
     * @return A string in the format to be saved to disk.
     */
    public abstract String toSaveString();

}
