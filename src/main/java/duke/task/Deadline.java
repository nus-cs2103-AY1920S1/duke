package duke.task;

/**
 * Encapsulates a deadline task.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline object with description, and date and time.
     *
     * @param description  Description of deadline.
     * @param by  Date and time of deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the deadline task to String format to write to hard disk.
     *
     * @return Deadline task in String format.
     */
    @Override
    public String convertTaskToString() {
        return String.format("D | %s | %s | %s", getStatus(), description, by);
    }

    /**
     * Converts the deadline task to String format for output.
     *
     * @return Deadline task in String format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
