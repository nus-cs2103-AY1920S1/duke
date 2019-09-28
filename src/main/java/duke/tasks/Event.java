package duke.tasks;

import duke.exception.DukeException;

import java.text.ParseException;
import java.util.Date;

public class Event extends Task {

    protected Date at;

    /**
     * Create an Event instance.
     * @param description information about th event
     * @param at the date and time of the event
     * @throws DukeException if errors occur in parsing the datetime
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = dateTimeFormatter.parse(at);
        } catch (ParseException e) {
            throw new DukeException("Incorrect Date/time format for the task.");
        }
    }

    /**
     * Generate the text representation of the event in display format.
     * @return the representation of the event in display format.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTimeFormatter.format(at) + ")";
    }
}
