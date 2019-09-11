package seedu.duke;

/**
 * Class for creating Task objects representing things to be done.
 */
public class ToDo extends Task {
    /** String representing type of Task. */
    protected String type = "ToDo";

    /** Constructor.*/
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns String representing type of Task object.
     * @return String representing type of Task representing things to be done.
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * Establishes String representation of object.
     * @return String representing the object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public void editTask(String description, String details) {
        this.description = description;
    }
}
