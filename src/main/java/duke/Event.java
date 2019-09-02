package duke;

import java.text.ParseException;
import java.util.Date;

public class Event extends Task {

    protected Date at;

    public Event(String description, String at) throws DukeException{
        this(description, false, at);
    }

    public Event(String description, boolean isDone, String at) throws DukeException {
        super(description, isDone);
        try {
            this.at = Duke.dateTimeFormatter.parse(at);
        } catch (ParseException e) {
            throw new DukeException("Oops! Incorrect Date/time format.");
        }
    }

    public String toSaveFormat() {
        return "E" + "`" + super.toSaveFormat() + "`" + Duke.dateTimeFormatter.format(at) + '\n';
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + Duke.dateTimeFormatter.format(at) + ")";
    }
}
