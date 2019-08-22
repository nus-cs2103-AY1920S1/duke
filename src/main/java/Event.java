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

    /**
     * Creates an event with a description and date/time information.
     * @param description string representing description of event.
     * @param dateTime string representing when the event will occur.
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Return a string representation of this event.
     *
     * @return string representing this event.
     */
    public String toString() {
        return "[E]" + super.toString() + "(at:" + dateTime + ")";
    }
}