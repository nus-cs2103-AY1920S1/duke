package duke.task;

/**
 * Represents a to-do which consists of a description with no particular deadline.
 */
public class Todo extends Task {

    protected String by;

    /**
     * Constructor for a to-do task.
     * @param description Description of the task.
     * @throws InvalidTaskDukeException If the to-do is invalid.
     */
    public Todo(String description) throws InvalidTaskDukeException {
        super(description);
    }

    public String getTime() {
        return "";
    }

    @Override
    public String toString() {
        return "[Todo] " + super.toString();
    }
}