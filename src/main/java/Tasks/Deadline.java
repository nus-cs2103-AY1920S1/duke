package Tasks;

import java.util.Date;

public class Deadline extends Task {
    public Deadline(String description, Date by) {
        super(description);
        super.date = by;
        super.type = "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + super.date + ")";
    }
}