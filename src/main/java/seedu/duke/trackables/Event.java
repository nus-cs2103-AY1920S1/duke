package seedu.duke.trackables;

import seedu.duke.exceptions.InvalidArgumentException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    private String getAtAsFormatedDate() {
        return new SimpleDateFormat("d/MM/yyyy HHmm").format(at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
            + getAtAsFormatedDate() + ")";
    }

    @Override
    public String getAsString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("E").append(" | ")
            .append(isDone ? "1" : "0")
            .append(" | ").append(this.description)
            .append(" | ").append(getAtAsFormatedDate());
        return sb.toString();
    }
}
