public class Event extends Task {
    protected String dateTime;

    public Event(String description, String dt) {
        super(description);
        this.dateTime = dt;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), getDescription(), getDateTime());
    }
}
