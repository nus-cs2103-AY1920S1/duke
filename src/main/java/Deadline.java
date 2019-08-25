/**
 * Deadline class. SubClass of Task.
 */
public class Deadline extends Task {
    /** Deadline date for the deadline. */
    private String by;
    /** DateTime of deadline. */
    private DateTime datetime;

    /**
     * Constructor.
     * @param description Description of the deadline.
     * @param by deadline for the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            datetime = new DateTime(by);
        } catch (InvalidDateTimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets the deadline for this deadline.
     * @return String deadline.
     */
    public String getBy() {
        return by;
    }

    /**
     * String representation of deadlines.
     * @return String representation of deadlines.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), datetime);
    }
}
