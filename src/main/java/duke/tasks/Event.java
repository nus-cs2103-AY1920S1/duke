package duke.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    private final Date start;
    private final Date end;

    /**
     * Initialises an event.
     *
     * @param description The event description
     * @param start       The time period during which the event occurs
     */
    public Event(String description, Date start, Date end) {
        super(description);
        this.start = (Date) start.clone();
        this.end = (Date) end.clone();
    }

    /**
     * Gets the date of the deadline.
     *
     * @return The date of the deadline.
     */
    @Override
    public Date getDate() {
        return (Date) start.clone();
    }

    /**
     * Returns out the string representation of an event for printing.
     *
     * @return The event string for printing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " \n(from: "
                + new SimpleDateFormat("EEE, d MMM yyyy, hh:mm a").format(start) + " \n to:  "
                + new SimpleDateFormat("EEE, d MMM yyyy, hh:mm a").format(end) + ")";
    }

    /**
     * Returns the string representation of an event for saving to file.
     *
     * @return The event string for storage.
     */
    @Override
    public String toStore() {
        return "E" + super.toStore() + "|"
                + new SimpleDateFormat("EEE, d MMM yyyy, hh:mm a").format(start) + "|"
                + new SimpleDateFormat("EEE, d MMM yyyy, hh:mm a").format(end);
    }
}