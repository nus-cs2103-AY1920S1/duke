import java.util.Date;
import java.time.LocalTime;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event.
 */
public class Event extends Task {
    private Date date;
    private LocalTime time;

    /**
     * Creates a new Event with given description, date and time.
     * @param description The description of Event.
     * @param date The date of Event.
     * @param time The time of Event.
     */
    public Event(String description, Date date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public String getDateString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        return dateFormatter.format(date);
    }

    public String getTimeString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
        return timeFormatter.format(time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDateString()  + " "
                + getTimeString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Event)) {
            return false;
        }

        Event other = (Event) o;
        return this.description == other.description;
    }
}
