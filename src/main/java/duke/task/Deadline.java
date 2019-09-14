package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.parser.exceptions.InvalidDateTimeException;
import duke.parser.DateHandler;

/**
 * Represents Deadline Tasks.
 * Instances are tasks that has a due date.
 */
public class Deadline extends Task {
    /** Deadline date for the deadline. */
    private String by;
    /** LocalDate of deadline. */
    private LocalDateTime datetime;

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
        try {
            datetime = DateHandler.getInstance().parse(by);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Deadline date not valid.");
        }
    }

    @Override
    public LocalDateTime getDate() {
        return datetime;
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
        return String.format("[D]%s (by: %s)",
                super.toString(),
                DateHandler.getInstance().format(datetime));
    }
}
