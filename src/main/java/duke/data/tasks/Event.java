package duke.data.tasks;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Implements an event task.
 * @author lyskevin
 */
public class Event extends Task {

    private Date dateAndTime;
    private SimpleDateFormat dateFormat;

    /**
     * Constructs an event task with the specified description.
     * @param description The specified description.
     */
    public Event(String description, String dateAndTime) throws ParseException {
        super(description);
        try {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dateFormat.setLenient(false);
            this.dateAndTime = dateFormat.parse(dateAndTime);
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     * Constructs an event task with the specified description and isDone status.
     * @param description The specified description.
     * @param isDone The specified isDone status.
     */
    public Event(String description, boolean isDone, String dateAndTime) throws ParseException {
        super(description, isDone);
        try {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dateFormat.setLenient(false);
            this.dateAndTime = dateFormat.parse(dateAndTime);
        } catch (ParseException e) {
            throw e;
        }
    }

    /**
     * Returns the String representation of this event task's date and time.
     * @return The String representation of this event task's date and time.
     */
    public String getDateAndTime() {
        return dateFormat.format(dateAndTime);
    }

    /**
     * Returns the String representation of this event task's type.
     * @return The String representation of this event task's type.
     */
    public String getType() {
        return "event";
    }

    /**
     * Returns the string representation of this event task.
     * @return The string representation of this event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), dateFormat.format(dateAndTime));
    }

}
