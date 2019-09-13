package duke.task;

import duke.exception.DateTimeParseDukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String description, String by) throws DateTimeParseDukeException {
        super(description);

        //@@adapted from CarbonGrid(exception)
        try {
            this.by = LocalDateTime.parse(by.trim());
        } catch (DateTimeParseException err) {
            throw new DateTimeParseDukeException();
        }
        //@@author
    }

    public String getDescription() {
        return super.getDescription() + " (by: " + by + ")";
    }

    public String getTaskTypeLetter() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + this.by.toString() + ")";
    }
}
