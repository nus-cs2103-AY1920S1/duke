package duke.task;

import duke.exception.DukeException;
import duke.util.Parser;

import java.util.Date;

/**
 *  A task which contains information about an Event with only a start date and time.
 */
public class EventTask extends Task {
    protected Date startDateTime;

    public EventTask(String description, String startDateTime) throws DukeException {
        super(description);
        this.startDateTime = Parser.parseDateTime(startDateTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toStorageFormat() {
        return String.join(
            " | ",
            new String[] {
                "E",
                getIsDone() ? "1" : "0",
                super.description,
                Parser.convertDateTimeToString(startDateTime) }
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + getDetails() + " (at: " + Parser.convertDateTimeToString(startDateTime) + ")";
    }
}
