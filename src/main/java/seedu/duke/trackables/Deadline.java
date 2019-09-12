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

    public Deadline(String... args) {
        super(args);
        this.by = args[3];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
            + new SimpleDateFormat("d/MM/yyyy HHmm").format(by) + ")";
    }

    @Override
    public String getAsString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("D").append(" | ")
            .append(isDone ? "1" : "0")
            .append(" | ").append(this.description)
            .append(" | ").append(this.by);
        return sb.toString();
    }
}
