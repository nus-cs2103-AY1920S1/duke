package duke.data.tasks;

import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Implements an event task.
 * @author Lim Yong Shen, Kevin
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
     * Returns the command used to create this event task.
     * @return The command used to create this event task.
     */
    @Override
    public String getCommandString() {
        return String.format("event %s /at %s", description, dateFormat.format(dateAndTime));
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
