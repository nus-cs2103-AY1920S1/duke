package duke.task;

/**
 * Represents a to-do without date and time.
 */
public class Todo extends Task {
    /**
     * Constructs a to-do with a description.
     *
     * @param description description of to-do.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a simplified summary of this to-do.
     *
     * @return simplified string representation.
     */
    @Override
    public String getSimplifiedRepresentation() {
        return "T | " + (super.isDone ? 1 : 0) + " | " + super.taskName;
    }

    /**
     * Returns a string representation of this to-do.
     *
     * @return string representation.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
