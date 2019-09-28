package duke.task;

/**
 * Represents a task without any date/time attached to it.
 */
public class Todo extends Task {
    /**
     * Constructor for todo task.
     *
     * @param description Description of todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the print format of the todo task.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the save format of the todo task.
     *
     * @return Formatted string to be saved in storage file.
     */
    @Override
    public String getSaveString() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}
