package duke.task;
/**
 * Represents a Event that contains the description of the Event and when it occurs.
 */
public class Event extends Task {
    protected String period;
    /**
     * Creates the Event Task Object.
     * @param description contains the description of the Event
     * @param period contains the information of when the Event occurs.
     */
    public Event(String description, String period) {
        super(description);
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + period + ")";
    }
}
