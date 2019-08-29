import java.text.ParseException;

public class Event extends Task {
    private DukeDate startTime;
    private DukeDate endTime;

    public Event(String description, String startTime, String endTime) throws ParseException {
        super(description);
        this.startTime = new DukeDate(startTime);
        this.endTime = new DukeDate(endTime);
    }

    public String toString() {
        String statusIcon = getStatusIcon();
        return "[E][" + statusIcon + "] " + this.description + " (at: " + this.startTime + " - " + this.endTime +  ")";
    }

    public String getStorageFormat() {
        String storageString = "E | " + super.getStorageFormat() + " | " + this.startTime + " | " + this.endTime;
        return storageString;
    }
}
