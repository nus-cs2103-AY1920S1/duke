package duke.task;

import duke.exception.DateTimeParseDukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    //formatting of inputs is left in User class
    protected LocalDateTime by;
    public Deadline(String description, String by) throws DateTimeParseDukeException {
        super(description);

        //@@adapted from CarbonGrid(exception)
        //realised I should make life easier by restricting input formats instead of accounting for all types.
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
