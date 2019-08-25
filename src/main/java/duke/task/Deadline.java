package duke.task;

import java.util.Date;

public class Deadline extends Task {
    private String by;
    private Date date;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        date = new Date(by);
    }

    @Override
    public String toWriteFile() {
        return "D | " + getDoneStatus() + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.toString() + ")";
    }
}
