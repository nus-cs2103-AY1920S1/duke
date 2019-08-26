package duke.task;

import duke.datetime.DateTime;
import duke.exception.InvalidDateTimeException;

/**
 * Represents Deadline Tasks.
 * Instances are tasks that has a due date.
 */
public class Deadline extends Task {
    /** Deadline date for the deadline. */
    private String by;
    /** DateTime of deadline. */
    private DateTime datetime;

    /**
     * Creates an instance of Deadline.
     *
     * @param description Description of the deadline.
     * @param by deadline for the deadline.
     * @throws InvalidDateTimeException If DateTime format is wrong.
     */
    public Deadline(String description, String by) throws InvalidDateTimeException {
        super(description);
        this.by = by;
        datetime = new DateTime(by);
    }

    /**
     * Returns the string representation for data file.
     *
     * @return Returns String representation for data file.
     */
    @Override
    public String toFileAsString() {
        return String.format("D - %s - %s - %s", isDone ? "1" : "0", description, by);
    }

    /**
     * Gets the deadline for this deadline.
     * @return String deadline.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns the String representation of deadlines.
     * @return String representation of deadlines.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), datetime);
    }
}
