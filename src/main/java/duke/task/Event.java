package duke.task;

import duke.datetime.DateTime;
import duke.exception.InvalidDateTimeException;

/**
 * Represents Event Tasks.
 * Instances are tasks with at a specific time.
 */
public class Event extends Task {
    /** Time of event. */
    private String at;
    /** DateTime of the event. */
    private DateTime datetime;

    /**
     * Creates an instance of Event.
     *
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
     * Returns the timing of the event.
     * @return String time;
     */
    public String getAt() {
        return at;
    }

    /**
     * Returns String representation of the Event.
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), datetime);
    }
}
