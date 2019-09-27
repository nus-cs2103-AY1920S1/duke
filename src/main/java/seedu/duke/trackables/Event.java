package seedu.duke.trackables;

import seedu.duke.ui.StringStore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an Event that starts at a specific time. It contains a description and a start date.
 */
public class Event extends Task {

    private Date attendDate;

    public Event(String description, Date attendDate)  {
        super(description);
        this.attendDate = attendDate;
    }

    /**
     * Constructs a new Event using string arguments. Typically used when restoring Event from Storage.
     *
     * @param args String arguments containing Event data from Storage.
     */
    public Event(String... args) {
        super(args);
        try {
            this.attendDate = new SimpleDateFormat(StringStore.DEFAULT_DATE_FORMAT).parse(args[3]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private String getAttendDateAsFormattedDate() {
        return new SimpleDateFormat(StringStore.DEFAULT_DATE_FORMAT).format(attendDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
            + getAttendDateAsFormattedDate() + ")";
    }

    @Override
    public String getAsString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("E").append(" | ")
            .append(isDone ? "1" : "0")
            .append(" | ").append(this.description)
            .append(" | ").append(getAttendDateAsFormattedDate());
        return sb.toString();
    }
}
