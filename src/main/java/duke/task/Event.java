package duke.task;

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
        this.at = at;
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