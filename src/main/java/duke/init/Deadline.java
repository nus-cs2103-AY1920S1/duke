package duke.init;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Implements a deadline task.
 * @author lyskevin
 */
public class Deadline extends Task {

    private Date dateAndTime;
    private SimpleDateFormat dateFormat;

    /**
     * Constructs a deadline task with the specified description.
     * @param description The specified description.
     */
    public Deadline(String description, String dateAndTime) throws ParseException {
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
     * Constructs a deadline task with the specified description and isDone status.
     * @param description The specified description.
     * @param isDone The specified isDone status.
     */
    public Deadline(String description, boolean isDone, String dateAndTime) throws ParseException {
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
     * Returns the String representation of this deadline task's date.
     * @return The String representation of this deadline task's date.
     */
    public String getDateAndTime() {
        return dateFormat.format(dateAndTime);
    }

    /**
     * Returns the String representation of this deadline task's type.
     * @return The String representation of this deadline task's type.
     */
    public String getType() {
        return "deadline";
    }

    /**
     * Returns the string representation of this deadline task.
     * @return The string representation of this deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dateFormat.format(dateAndTime));
    }

}
