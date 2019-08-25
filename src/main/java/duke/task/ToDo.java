package duke.task;

/**
 * Represents a ToDo Task.
 */
public class ToDo extends Task {
    /**
     * Creates an instance of a ToDo Task.
     *
     * @param description Description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns String representation of a todo task.
     *
     * @return String representation of a todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
