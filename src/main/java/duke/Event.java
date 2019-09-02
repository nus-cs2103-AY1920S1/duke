package duke;

import java.text.ParseException;
import java.util.Date;

public class Event extends Task {

    protected Date at;

    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = Duke.dateTimeFormatter.parse(at);
        } catch (ParseException e) {
            throw new DukeException("Oops! Incorrect Date/time format.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Duke.dateTimeFormatter.format(at) + ")";
    }
}
