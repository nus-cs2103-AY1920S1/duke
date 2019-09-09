package duke.task;

import duke.exception.DateTimeParseDukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime at;
    public Event(String description, String at) throws DateTimeParseDukeException {
        super(description);

        try {
            this.at = LocalDateTime.parse(at.trim());
        } catch (DateTimeParseException err) {
            throw new DateTimeParseDukeException();
        }
    }

    public String getDescription() {
        return super.getDescription() + " (at: " + at + ")";
    }

    public String getTaskTypeLetter() {
        return "E";
    }

    public String getDateTime() { //format?
        return this.at.toString();
    }

    //@@author CarbonGrid
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.toString() + ")\n";
    }
}
