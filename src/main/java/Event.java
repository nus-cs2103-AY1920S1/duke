public class Event extends Task {

    protected DateTime dateTime;
    protected String timeInFile;

    public Event(String description, String dateTimeString) {
        super(description);
        this.dateTime = dateTime.create(dateTimeString);
        this.timeInFile = dateTimeString;
    }

    @Override
    public String toSave() {
        return "E | " + super.getBinaryStatus() + " | " + super.description + " | " + timeInFile;
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + dateTime + ")";
    }
}