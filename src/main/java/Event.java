public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        this.type = "event";
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "]" + description + " (at: " + at + ")";
    }
}