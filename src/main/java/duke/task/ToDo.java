package duke.task;

/**
 * A Task that contains both a description of the task to be completed at any time.
 */
public class ToDo extends Task {

    /**
     * Constructs an instance of a task that needs to be done.
     *
     * @param description the description of that task.
     */
    public ToDo(String description) {
        super(description);

    }

    /**
     * Provides the string representation of an instance of this task.
     *
     * @return the task's string representation.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), getDescription());
    }

}
