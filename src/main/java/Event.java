import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is a class for event tasks.
 * @author Choong Yong Xin
 */

public class Event extends Task {

    Date atInDateFormat;
    String atInStringFormat;

    /**
     * Constructor for Event.
     *
     * @param description Event description
     * @param eventDate Event date
     */
    public Event(String description, String eventDate) throws WrongDateFormatDukeException {
        super(description);
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            this.atInDateFormat = dateFormat.parse(eventDate);
            this.atInStringFormat = eventDate;
        } catch (Exception e) {
            throw new WrongDateFormatDukeException();
        }
    }

    /**
     * Returns string for task display.
     *
     * @return display string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atInStringFormat + ")";
    }

    /**
     * Returns string for file writing.
     *
     * @return string to be saved.
     */
    @Override
    public String stringForAppend() {
        return "E | " + super.getStatusIcon() + " | " + description + " | " + atInStringFormat;
    }
}