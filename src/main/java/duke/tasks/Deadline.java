package duke.tasks;

import java.text.ParseException;
import java.util.Date;

import duke.exception.DukeException;

public class Deadline extends Task {

    protected Date by;

    /**
     * Create a deadline task instance.
     * @param description information about the deadline task
     * @param by the deadline in string format
     * @throws DukeException if errors occur in parsing the deadline
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = dateTimeFormatter.parse(by);
        } catch (ParseException e) {
            throw new DukeException("Incorrect date/time format for the task.");
        }
    }

    /**
     * Generate the text representation of the deadline task in display format.
     * @return the representation of the deadline task in display format.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimeFormatter.format(by) + ")";
    }
}
