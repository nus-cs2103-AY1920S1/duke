package duke.task;

/**
 * Event class to create event task.
 *
 * @author TeoShyanJie
 *
 */
public class Event extends Task {
    /** The event deadline of the task. */
    protected String at;

    /**
     * Constructor of the event class.
     * @param description Event description of the class.
     * @param at Event deadline of the class.
     */
    public Event(String description, String at) {
        super(description);
        super.time = at;
        this.at = at;
    }

    /**
     * Get time of event.
     * @return String of event time.
     */
    public String getAt() {
        return at;
    }

    /**
     * Return event task as string.
     * @return task as string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}