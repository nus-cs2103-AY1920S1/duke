package duke.task;

import duke.exception.DateTimeParseDukeException;
import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Constructs a duke.task.Deadline with description and DateTime string.
     *
     * @param description Description
     * @param by          DateTime String
     * @throws DukeException On empty fields or DateTime string parse failure
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by.trim(), super.inDateTimeFormat());
        } catch (DateTimeParseException e) {
            throw new DateTimeParseDukeException();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(super.outDTF) + ")\n";
    }

    public String childClass() {
        return "deadline";
    }

    public String toFileString() {
        return "D" + super.toFileString() + (char) 31 + this.by.format(super.fileDTF);
    }
}
