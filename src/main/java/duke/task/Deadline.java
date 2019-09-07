package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    private final Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, Date by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toEncodedString() {
        String isDone = this.isDone ? "1" : "0";
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
        return "D | " +  isDone + " | " + this.description + " | " + dateFormat.format(this.by);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + dateFormat.format(this.by) + ")";
    }

}
