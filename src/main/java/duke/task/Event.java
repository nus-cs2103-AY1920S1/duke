package duke.task;

/**
 * Event tasks that extends from Task class. Keeps track of the event
 * description followed by the timing indicated by "/at".
 */
public class Event extends Task {
    /**
     * This field stores the timing when the event is going to occur.
     */
    private String at;

    /**
     * Constructor for the Event class. Takes note of the description,
     * followed by the timing in which the event is going to occur.
     *
     * @param description Gives the main outline of the task.
     * @param at Gives the timing in which the event is occurring.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Getter method which gets the timing the event will start.
     *
     * @return the timing when the event will start.
     */
    public String getAt() {
        return at;
    }

    /**
     * Formats the event task into a String.
     *
     * @return the event task formatted as a string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
