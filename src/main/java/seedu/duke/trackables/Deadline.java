package seedu.duke.trackables;

import seedu.duke.exceptions.InvalidArgumentException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected Date by;

    public Deadline(String description, Date by) throws InvalidArgumentException {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
            + new SimpleDateFormat("d/MM/yyyy HHmm").format(by) + ")";
    }

}
