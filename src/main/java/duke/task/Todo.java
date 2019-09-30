package duke.task;

/**
 * A representation of the todo task.
 */
public class Todo extends Task {
    /**
     * Public constructor for Todo class.
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts information about the todo task to String format (for addition to the data file).
     *
     * @return Task information in String format.
     */
    @Override
    public String printToTxtFile() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    /**
     * Converts information about the todo task to String format (for printing to the UI).
     *
     * @return Task information in String format.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
