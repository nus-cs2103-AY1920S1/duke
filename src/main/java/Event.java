public class Event extends Task {
    protected String at;

    // to manage incoming event list at specific location
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String newAt) {
        this.at = newAt;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + at + ")";
    }
}