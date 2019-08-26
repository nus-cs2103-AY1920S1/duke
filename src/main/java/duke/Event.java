package duke;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Represents an Event object. A <code>Event</code> object corresponds to
 * an occasion to be at.
 */
public class Event extends Task {
    private Date date;
    private String description;

    public Event(String description) {
        super(description);
        this.type = "E";
    }

    /**
     * Sets the date of the event in a simple date format.
     * If unable to parse the date, exception is thrown.
     *
     * @param time  String containing date and time of event.
     * @throws ParseException  If unable to parse date.
     */
    public void parseTime(String time) throws ParseException {
        this.date = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(time);
    }

    /**
     * Returns the string representation of the Event object.
     * @return String
     */
    @Override
    public String toString() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.description + this.date;
    }

    /**
     * Returns true if two instances of Event are equal.
     * Otherwise, returns false.
     *
     * @param o  An object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event event = (Event) o;
            return this.toString().equals(event.toString());
        }
        return false;
    }
}
