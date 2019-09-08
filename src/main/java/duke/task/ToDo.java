package duke.task;

import java.time.LocalDateTime;

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

    @Override
    public LocalDateTime getDate() {
        return null;
    }

    /**
     * Returns the string representation for data file.
     *
     * @return Returns String representation for data file.
     */
    @Override
    public String toFileAsString() {
        return String.format("T - %s - %s", isDone ? "1" : "0", description);
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
