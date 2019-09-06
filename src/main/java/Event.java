/**
 * Represents an event object.
 */
public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = "event";
        this.symbol = "E";
    }

    public String getDateString() {
        return this.at;
    }

    @Override
    public String toString() {

        return this.getSymbol() + this.getStatusIcon() + this.getDescription() + " (at: " + this.at + ")";
    }
}