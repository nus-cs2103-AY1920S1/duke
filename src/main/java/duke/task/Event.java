package duke.task;

import duke.exception.DateTimeParseDukeException;
import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private LocalDateTime at;

    /**
     * Constructs an event with description and DateTime string.
     *
     * @param description Description
     * @param at          DateTime String
     * @throws DukeException On empty fields or DateTime string parse failure
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDateTime.parse(at.trim(), super.inDateTimeFormat());
        } catch (DateTimeParseException e) {
            throw new DateTimeParseDukeException();
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(super.outDTF) + ")\n";
    }

    public String childClass() {
        return "event";
    }
}
