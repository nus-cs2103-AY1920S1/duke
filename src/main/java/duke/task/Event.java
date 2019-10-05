package duke.task;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Represents an event that occurs at the user specified time and date.
 */
public class Event extends Task implements Comparable<Event> {

    /**
     * Represents the date and time at which the event occurs.
     */
    protected Date at;

    /**
     * Class constructor which specifies the description of the task and the time at which the
     * event takes place.
     *
     * @param description The description of the event.
     * @param at The date and the time at which the event occurs
     */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    private Date getAt() {
        return this.at;
    }

    @Override
    public String toString() {
        SimpleDateFormat properFormat = new SimpleDateFormat("dd 'of' MMMM yyyy, hh:mm a");
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (at: " + properFormat.format(this.at)
                + ")";
    }

    @Override
    public int compareTo(Event o) {
        return (this.at).compareTo(o.getAt());
    }
}
