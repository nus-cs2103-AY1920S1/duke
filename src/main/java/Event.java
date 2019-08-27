public class Event extends Task {

    public Event(String description, String date) {
        super(description);
        this.date = date;
        this.type = "event";
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "]" + description + " (at: " + date + ")";
    }
}