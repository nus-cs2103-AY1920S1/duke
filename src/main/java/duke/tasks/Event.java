package duke.tasks;

import duke.DukeException;

import java.text.ParseException;
import java.util.Date;

public class Event extends Task {

    protected Date at;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = dateTimeFormatter.parse(at);
        } catch (ParseException e) {
            throw new DukeException("Incorrect Date/time format for the task.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTimeFormatter.format(at) + ")";
    }
}
