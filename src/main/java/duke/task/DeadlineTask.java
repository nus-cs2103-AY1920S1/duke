package duke.task;

import duke.exception.DukeException;
import duke.util.Parser;

import java.util.Date;

/**
 * A task which has a deadline in the Duke Program.
 */
public class DeadlineTask extends Task {
    Date dueDateTime;

    public DeadlineTask(String description, String dueDateTime) throws DukeException {
        super(description);
        this.dueDateTime = Parser.parseDateTime(dueDateTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toStorageFormat() {
        return String.join(
            " | ",
            new String[] {
                "D",
                getIsDone() ? "1" : "0",
                super.description,
                Parser.convertDateTimeToString(dueDateTime)
            }
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Parser.convertDateTimeToString(dueDateTime) + ")";
    }
}
