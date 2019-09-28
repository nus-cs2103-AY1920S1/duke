package duke.task;

/**
 * A Task that contains both a description and a date where the task takes place.
 */
public class Event extends TimeLimitTask {
    /**
     * Constructs an instance of an event task.
     *
     * @param description the description fo the event task.
     * @param dt the date of that event task.
     */
    public Event(String description, String dt) {
        super(description, dt);
    }

    /**
     * Informs the event date.
     *
     * @return event date.
     */
    public String getEventDate() {
        return getDateTime();
    }

    /**
     * Provides the string representation of an instance of event task.
     *
     * @return the event task's string representation.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), getDescription(), getEventDate());
    }
}
