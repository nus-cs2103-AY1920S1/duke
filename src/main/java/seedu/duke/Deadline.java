package seedu.duke;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    protected Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getFullDescription() {
        return description + " . " + by.getTime();
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMMM yyyy hh:mm a");
        String date = formatter.format(by);
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
