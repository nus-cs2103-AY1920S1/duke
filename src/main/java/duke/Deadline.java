package duke;

import java.text.ParseException;
import java.util.Date;

public class Deadline extends Task {

    protected Date by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = Duke.dateTimeFormatter.parse(by);
        } catch (ParseException e) {
            throw new DukeException("Oops! Incorrect date/time format.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Duke.dateTimeFormatter.format(by) + ")";
    }
}
