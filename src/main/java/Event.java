/**
 * Represents a task that starts and ends at a specific time.
 * A <code>Event</code> object corresponds to a task represented by
 * two Strings e.g., <code>"team project meeting","2/10/2019 2-4pm"</code>
 */
public class Event extends Task {

    /** Event time for this task. */
    protected String at;

    /**
     * Constructs a <code>Event</code> with a task description and
     * task event time.
     *
     * @param description Description of task.
     * @param at Event time of task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns condensed description of specified event task.
     *
     * @return Condensed description.
     */
    public String formattedString() {
        return String.format("D | %d | %s | %s", super.isDone ? 1 : 0, super.description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}