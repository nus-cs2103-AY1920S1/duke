package duke.tasks;

public class ToDo extends Task {

    /**
     * Initialises a to-do task.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * The string representation of a to-do for printing.
     *
     * @return The to-do string for printing.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * The string representation of a to-do for saving to file.
     *
     * @return The to-do string for storage.
     */
    @Override
    public String toStore() {
        return "T" + super.toStore();
    }
}