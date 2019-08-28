import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Encapsulates a event-type task handled by Duke.
 *
 * A event differs from other tasks because it has a date on which it is to
 * occur.
 *
 * @author atharvjoshi
 * @version CS2103 AY19/20 Sem 1 iP Week 2
 */
public class Event extends Task {
    /** The date or time at which event is slated to occur */
    private String dateTime;

    /** Date / Time converted to Date format */
    private Date when;

    /**
     * Creates an event with a description and date/time information.
     * @param description string representing description of event.
     * @param dateTime string representing when the event will occur.
     */
    public Event(String description, String dateTime) throws ParseException {
        super(description);
        this.dateTime = dateTime;
        this.when = this.toDate();
    }

    /**
     * Return a string representation of this event.
     *
     * @return string representing this event.
     */
    public String toString() {
        return "[E]" + super.toString() + "(at:" + dateTime + ")";
    }

    public Date toDate() throws ParseException {
        try {
            return new SimpleDateFormat("dd/MM/yyyy HHmm").parse(this.dateTime);
        } catch (ParseException exception) {
            System.out.println("Looks like you entered the incorrect Date/Time "
                    + "format. Please follow <dd>/<mm>/<yyyy> <hhmm>");
        }
        return null;
    }
}