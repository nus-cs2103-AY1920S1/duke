public class Event extends Task {

    protected DateTime dateTime;

    public Event(String description, String dateTimeString) {
        super(description);
        this.dateTime = dateTime.create(dateTimeString);
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + dateTime + ")";
    }
}