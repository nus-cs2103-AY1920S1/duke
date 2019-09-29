package duke.task;

import java.util.Date;

public class Deadline extends Task {
    private Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
        this.type = "D";
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
