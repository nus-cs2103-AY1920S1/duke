package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an Event Task.
 */
public class EventTask extends Task {
    private Date details;

    public EventTask(String description, Date details) {
        super(description);
        this.details = details;
    }

    public EventTask(String description, Date details, boolean done) {
        super(description, done);
        this.details = details;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + details + ")";
    }

    @Override
    public String store() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HHmm");
        return "E|" + getStatus() + "|" + description + "|" + formatter.format(details);
    }
}