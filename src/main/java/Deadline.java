import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

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
}
