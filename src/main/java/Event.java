/**
 * Represents an Event task.
 */
public class Event extends Task {

    private String when;

    /**
     * Creates an Event task with the associated description and event day and time.
     * @param desc Describes the event.
     * @param when The event day and event time.
     */
    public Event(String desc, String when) {
        super(desc);
        this.when = when;
    }

    public String getWhen() {
        return this.when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", "E", super.getDoneSymbol(), this.desc, this.when);
    }
}
