package duke;

/**
 * The Event class creates a Event task
 * that extends from the Task class.
 */
public class Event extends Task {

    private String at;

    /**
     * Constructor for class Event.
     *
     * @param description The Event task.
     * @param at The date/time of event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the date and time of the event.
     *
     * @return at Date and time of event.
     */
    public String getAt() {
        return at;
    }

    /**
     * Overrides the toString method to print the event task.
     *
     * @return String Event task formatted to string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}