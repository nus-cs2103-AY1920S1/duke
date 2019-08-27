package seedu.duke;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Deadline extends Task {

    protected Date by;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public Deadline(String task, String by) throws DukeException {
        super(task);
        try {
            this.by = dateFormatter.parse(by);
        } catch (ParseException e) {
            throw new DukeWrongDateFormatException();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateFormatter.format(by) + ")";
    }
}
