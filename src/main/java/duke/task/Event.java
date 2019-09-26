package duke.task;

import duke.exception.DukeException;

import java.util.Date;

/**
 * Represents an event task object.
 */
public class Event extends Task {
    protected Date at;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = parseDate(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

    @Override
    public String textFormat() {
        return String.format("E | %d | %s | %s", getStatusCode(), description, formatDate(at));
    }
}