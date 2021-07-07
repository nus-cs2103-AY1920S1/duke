package duke.task;

/**
 * Encapsulates a to-do task.
 */
public class ToDo extends Task {
    /**
     * Constructs a To-Do object with description.
     *
     * @param description  Description of to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the to-do task to String format to write to hard disk.
     *
     * @return To-do task in String format.
     */
    @Override
    public String convertTaskToString() {
        return String.format("T | %s | %s", getStatus(), description);
    }

    /**
     * Converts the to-do task to String format for output.
     *
     * @return To-do task in String format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
