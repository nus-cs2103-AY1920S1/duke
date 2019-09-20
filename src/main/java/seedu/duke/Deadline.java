package seedu.duke;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for creating Deadline objects.
 */
public class Deadline extends Task {
    /** Date representing the deadline details (date and time). */
    protected Date by;
    /** String representing type of Task. */
    protected String type = "Deadline";

    /** Constructor. */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns String that represents the Deadline information to be documented in File.
     * @return String of Deadline information.
     */
    @Override
    public String getFullDescription() {
        return description + " . " + by.getTime();
    }

    /**
     * Returns String representing type of Task object.
     * @return String representing Deadline type.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Establishes String representation of object.
     * @return String representing the object.
     */
    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMMM yyyy hh:mm a");
        String date = formatter.format(by);
        return "[D]" + super.toString() + " (by: " + date + ")";
    }

    @Override
    public void editTask(String description, String details) {
        this.description = description;
        this.by = AddCommand.convertToDate(details);
    }
}
