package seedu.duke.trackables;

import seedu.duke.ui.StringStore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Deadline that needs to be met. It contains a description and a completion deadline.
 */
public class Deadline extends Task {

    private Date completeBy;

    public Deadline(String description, Date completeBy) {
        super(description);
        this.completeBy = completeBy;
    }

    /**
     * Constructs a new Deadline using string arguments. Typically used when restoring Deadline from Storage.
     *
     * @param args String arguments containing Deadline data from Storage.
     */
    public Deadline(String... args) {
        super(args);
        try {
            this.completeBy = new SimpleDateFormat(StringStore.DEFAULT_DATE_FORMAT).parse(args[3]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getCompleteByAsFormattedString() {
        return new SimpleDateFormat(StringStore.DEFAULT_DATE_FORMAT).format(completeBy);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
            + getCompleteByAsFormattedString() + ")";
    }

    @Override
    public String getAsString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("D").append(" | ")
            .append(isDone ? "1" : "0")
            .append(" | ").append(this.description)
            .append(" | ").append(getCompleteByAsFormattedString());
        return sb.toString();
    }
}
