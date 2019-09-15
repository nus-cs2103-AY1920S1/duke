package duke.task;

import duke.exception.DateTimeParseDukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Instantiates a Task object of type Event.
     * @param description Str to describe deadline
     * @param at Str, time of deadline
     * @throws DateTimeParseDukeException when datetime is of invalid format
     */
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

    //@@author CarbonGrid
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.toString() + ")";
    }
}
