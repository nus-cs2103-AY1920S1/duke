package seedu.duke.trackables;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an Event that starts at a specific time. It contains a description and a start date.
 */
public class Event extends Task {

    private Date at;

    public Event(String description, Date at)  {
        super(description);
        this.at = at;
    }

    /**
     * Constructs a new Event using string arguments. Typically used when restoring Event from Storage.
     *
     * @param args String arguments containing Event data from Storage.
     */
    public Event(String... args) {
        super(args);
        try {
            this.at = new SimpleDateFormat("d/MM/yyyy HHmm").parse(args[3]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String getAtAsFormattedDate() {
        return new SimpleDateFormat("d/MM/yyyy HHmm").format(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
            + getAtAsFormattedDate() + ")";
    }

    @Override
    public String getAsString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("E").append(" | ")
            .append(isDone ? "1" : "0")
            .append(" | ").append(this.description)
            .append(" | ").append(getAtAsFormattedDate());
        return sb.toString();
    }
}
