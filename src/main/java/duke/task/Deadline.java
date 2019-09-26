package duke.task;

import duke.exception.DukeException;

import java.util.Date;

/**
 * Represents a deadline task object.
 */
public class Deadline extends Task {
    protected Date by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = parseDate(by);
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    @Override
    public String textFormat() {
        return String.format("D | %d | %s | %s", getStatusCode(), description, formatDate(by));
    }
}
