package tasks;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Encapsulates a event-type task handled by uDuke.
 *
 * <p> A event differs from other tasks because it has a date on which it is to
 * occur. </p>
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 4
 */
public class Event extends Task {
    /** String representing date or time at which event is slated to occur. */
    private String dateTime;

    /** Date / Time converted to the Date format. */
    private Date when;

    /**
     * Creates an event with a description and date/time information.
     *
     * @param description string representing description of event.
     * @param dateTime string representing when the event will occur.
     * @param isDone flag indicating whether task has been done or not
     * @throws ParseException if the date/time is entered in the wrong format
     */
    public Event(String description, String dateTime, boolean isDone)
            throws ParseException {
        super(description, isDone);
        this.dateTime = dateTime;
        this.when = this.toDate();
    }

    /**
     * Return a string representation of this event.
     *
     * @return string representing this event.
     */
    public String toString() {
        return "E-" + super.toString() + "-" + dateTime;
    }

    /**
     * Converts string representation of date/time into an actual date/time
     * object.
     *
     * @return the parsed date and time information
     * @throws ParseException if the date/time is entered in the wrong format
     */
    private Date toDate() throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy HHmm").parse(this.dateTime);
    }
}