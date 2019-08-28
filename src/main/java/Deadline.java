import java.time.LocalDateTime;

/**
 * Encapsulates a deadline.
 * Each deadline has a time when the deadline takes effect,
 * stored in both String and LocalDateTime formats.
 * A subclass of Task.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDateTime dateTimeBy = null;

    /**
     * Constructor.
     * @param description description of the deadline
     * @param by date and time when deadline takes effect
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public void setDateTimeBy(LocalDateTime dateTimeBy) {
        this.dateTimeBy = dateTimeBy;
    }

    /**
     * Overridden toString method. Converts a deadline object into string form to be used
     * in to-do list display.
     * @return string representation of a task on the to-do list
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Overridden toTextFileString method. Converts a deadline object into string form to be used
     * in storage text file.
     * @return string representation of a task on the storage text file
     */
    @Override
    public String toTextFileString() {
        return "D|" + super.toTextFileString() + "|" + by;
    }
}
