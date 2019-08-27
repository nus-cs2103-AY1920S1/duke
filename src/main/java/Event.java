public class Event extends Task {

    protected DateTime dateTime;

    public Event(String description, String dateTimeString) {
        super(description);
        this.dateTime = dateTime.create(dateTimeString);
    }

    @Override
    public String toSave() {
        return "E | " + super.getBinaryStatus() + " | " + super.description + " | " + time;
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + dateTime + ")";
    }
}