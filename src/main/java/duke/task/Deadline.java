package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, Date by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getType() {
        return "D";
    }

    public Date getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by);
    }
}
