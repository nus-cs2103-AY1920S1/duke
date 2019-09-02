package duke;

import java.text.ParseException;
import java.util.Date;

public class Deadline extends Task {

    protected Date by;

    public Deadline(String description, String by) throws DukeException{
        this(description, false, by);
    }

    public Deadline(String description, boolean isDone, String by) throws DukeException {
        super(description, isDone);
        try {
            this.by = Duke.dateTimeFormatter.parse(by);
        } catch (ParseException e) {
            throw new DukeException("Oops! Incorrect date/time format.");
        }
    }

    public String toSaveFormat() {
        return "D" + "`" + super.toSaveFormat() + "`" + Duke.dateTimeFormatter.format(by) + '\n';
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Duke.dateTimeFormatter.format(by) + ")";
    }
}
