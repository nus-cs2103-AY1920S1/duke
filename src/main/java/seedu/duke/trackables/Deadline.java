package seedu.duke.trackables;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Deadline that needs to be met. It contains a description and a completion deadline.
 */
public class Deadline extends Task {

    private Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline using string arguments. Typically used when restoring Deadline from Storage.
     *
     * @param args String arguments containing Deadline data from Storage.
     */
    public Deadline(String... args) {
        super(args);
        try {
            this.by = new SimpleDateFormat("d/MM/yyyy HHmm").parse(args[3]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getByAsFormattedString() {
        return new SimpleDateFormat("d/MM/yyyy HHmm").format(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
            + getByAsFormattedString() + ")";
    }

    @Override
    public String getAsString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("D").append(" | ")
            .append(isDone ? "1" : "0")
            .append(" | ").append(this.description)
            .append(" | ").append(getByAsFormattedString());
        return sb.toString();
    }
}
