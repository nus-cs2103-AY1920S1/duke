package duke.task;

import duke.exception.DukeException;
import duke.util.Formatter;
import duke.util.Parser;
import java.time.LocalDateTime;

/**
 * Deadline class.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs a Deadline object.
     *
     * @param description Description of deadline.
     * @param by Due date of deadline.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = Parser.parseDateTime(by);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toDataString() {
        return "D | " + super.toDataString() + " | " + Formatter.standardFormatDateTime(this.by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Formatter.prettyFormatDateTime(by) + ")";
    }
}
