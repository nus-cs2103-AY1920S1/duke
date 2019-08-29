import java.text.ParseException;

public class Deadline extends Task {
    private DukeDate deadline;

    public Deadline(String description, String deadline) throws ParseException {
        super(description);
        this.deadline = new DukeDate(deadline);
    }

    public String toString() {
        String statusIcon = getStatusIcon();
        return "[D][" + statusIcon + "] " + this.description + " (by: " + this.deadline + ")";
    }

    public String getStorageFormat() {
        String storageString = super.getStorageFormat() + " | " + this.deadline;
        return storageString;
    }
}
