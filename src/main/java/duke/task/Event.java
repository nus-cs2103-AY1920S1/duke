package duke.task;

import duke.datetime.DateTime;
import duke.exception.InvalidDateTimeException;

/**
 * Event class. SubClass of Task.
 */
public class Event extends Task {
    /** Time of event. */
    private String at;
    /** DateTime of the event. */
    private DateTime datetime;

    /**
     * Constructor.
     * @param description Description of event.
     * @param at Time of the event.
     * @throws InvalidDateTimeException If DateTime format is wrong.
     */
    public Event(String description, String at) throws InvalidDateTimeException {
        super(description);
        this.at = at;
        datetime = new DateTime(at);
    }

    /**
     * Gets the timing of the event.
     * @return String time;
     */
    public String getAt() {
        return at;
    }

    /**
     * String representation of the Event.
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), datetime);
    }
}
