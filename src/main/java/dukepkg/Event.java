package dukepkg;

/**
 * The task of type Event.
 */
public class Event extends dukepkg.Task {
    private final String at;

    /**
     * Instantiates a new Event.
     *
     * @param task the task
     * @param at   the time at which the event will happen.
     */
    public Event(String task, String at) {
        super(task);
        this.at = at;
    }

    /**
     * Gets the time at which the event will happen.
     *
     * @return the time
     */
    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
