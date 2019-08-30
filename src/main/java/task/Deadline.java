package task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Defines the tasks with deadlines and provides it's completion status.
 */
public class Deadline extends Task {
    private Date by;

    /**
     * Constructs the Deadline object with specified completion status.
     * @param description Task description.
     * @param isDone Task completion status.
     * @param by Task's deadline.
     */
    public Deadline(String description, boolean isDone, Date by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Constructs the Deadline object with default completion status of FALSE.
     * @param description Task description.
     * @param by Task's deadline.
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }


    /**
     * Formats the date for the deadline.
     * @return Formatted date with specified date pattern.
     */
    public String stringDate() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(by);
    }

    /**
     * Returns the literal description of the object.
     * @return Understandable description of object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + stringDate() + ")";
    }

    /**
     * Formats the object so that it can be save into file.
     * @return Formatted description of the object.
     */
    public String toFile() {
        return "D | " + super.getStatusIcon() + " | " + super.getDescription() + " | " + stringDate();
    }
}