package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents a event task.
 */
public class Event extends Task {
    protected String at;
    protected Date date;

    /**
     * Constructs an event class.
     * @param description description of the event task.
     * @param at date of event.
     * @throws ParseException if date is in wrong format.
     */
    public Event(String description, String at) throws ParseException {
        super(description);
        this.at = at;
        this.date = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(at);
    }

    /**
     * Calculates the number of days to the event.
     *
     * @return the number of days between current date versus event date.
     */

    public int getDiffDays() {
        Calendar cal = Calendar.getInstance();
        Date current = cal.getTime();
        long diff = date.getTime() - current.getTime();
        int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
        return diffDays;
    }

    /**
     * Deals with changing the task to file format string.
     *
     * @return task as string.
     */
    public String format() {
        return "E" + super.format() + "|" + at;
    }

    /**
     * Deals with changing the task to print format string.
     *
     * @return task as string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
