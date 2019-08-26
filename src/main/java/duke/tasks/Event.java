package duke.tasks;

public class Event extends Task {

    private final String range;

    /**
     * Initialises an event.
     *
     * @param description The event description
     * @param range       The time period during which the event occurs
     */
    public Event(String description, String range) {
        super(description);
        this.range = range;
    }

    /**
     * Returns out the string representation of an event for printing.
     *
     * @return The event string for printing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + range + ")";
    }

    /**
     * Returns the string representation of an event for saving to file.
     *
     * @return The event string for storage.
     */
    @Override
    public String toStore() {
        return "E" + super.toStore() + "|" + range;
    }
}