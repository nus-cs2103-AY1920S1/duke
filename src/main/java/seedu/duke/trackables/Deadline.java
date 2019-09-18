package seedu.duke.trackables;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            this.completeBy = new SimpleDateFormat("d/MM/yyyy HHmm").parse(args[3]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getCompleteByAsFormattedString() {
        return new SimpleDateFormat("d/MM/yyyy HHmm").format(completeBy);
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
