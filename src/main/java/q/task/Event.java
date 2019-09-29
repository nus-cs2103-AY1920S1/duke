package q.task;

import q.main.DateTimeParser;

import java.util.Date;

/**
 * An Event Task. Has a description and a date and time of the event.
 */
public class Event extends Task {
    private Date at;

    /**
     * Constructs an undone event with a description and date.
     *
     * @param description The description of the event.
     * @param at          The date and time of the event.
     */
    public Event(String description, Date at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructs a event with a description, date, and a doneness.
     *
     * @param description The description of the event
     * @param isDone      The doneness of the event.
     * @param at          The date and time of the event.
     */
    public Event(String description, boolean isDone, Date at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Converts the task to a string, including the doneness of the event, its description, and the date and time.
     *
     * @return A string representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTimeParser.format(at) + ")";
    }

    /**
     * Converts the event to an encoded string for saving.
     *
     * @return A string encoding the event for saving.
     */
    public String toSave() {
        return "E | " + (super.isDone ? 1 : 0) + " | " + super.description + " | " + DateTimeParser.format(at);
    }
}
