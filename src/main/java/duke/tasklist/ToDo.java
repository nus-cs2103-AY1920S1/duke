package duke.tasklist;

/**
 * A Task in a TaskList that represents a simple ToDo Task with a description.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo Task.
     *
     * @param description The description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a ToDo Task, with the specified completion status.
     *
     * @param isComplete  The completion status of the Task
     * @param description The description of the Task
     */
    public ToDo(boolean isComplete, String description) {
        super(description);
        taskCompletionStatus = isComplete;
    }

    /**
     * Returns the string representation of the ToDo Task.
     *
     * @return The string representation of the ToDo Task
     */
    @Override
    public String toString() {
        return "[T]".concat(super.toString());
    }
}
