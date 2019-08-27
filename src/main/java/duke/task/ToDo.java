package duke.task;

/**
 * Encapsulates a todo task.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo object with description.
     *
     * @param description  Description of todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the todo task to String format to write to hard disk.
     *
     * @return Todo task in String format.
     */
    @Override
    public String convertTaskToString() {
        return String.format("T | %s | %s", this.getStatus(), this.description);
    }

    /**
     * Converts the todo task to String format for output.
     *
     * @return Todo task in String format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
