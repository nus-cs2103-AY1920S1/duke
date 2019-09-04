package duke.tasks;

import duke.DukeException;

import java.text.ParseException;
import java.util.Date;

public class Deadline extends Task {

    protected Date by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = dateTimeFormatter.parse(by);
        } catch (ParseException e) {
            throw new DukeException("Incorrect date/time format for the task.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimeFormatter.format(by) + ")";
    }
}
