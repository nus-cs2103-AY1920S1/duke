package tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Encapsulates a Task object of the type Deadline.
 * Represents a Deadline task that has specific due date / due time.
 */
public class Deadline extends Task {
    protected Date endDate;

    /**
     * Constructs a new Deadline task.
     *
     * @param description This is the short description of the task.
     * @param endDate This specifies when the Deadline Task expires.
     *                It should include the due date or due time or both.
     *                The format should follow "by: Day Time" strictly.
     *                The date and time are not treated as strings.
     */
    public Deadline(String description, Date endDate) {
        super(description);
        this.endDate = endDate;
        this.typeOfTask = "D";
    }

    /**
     * Getter function that returns the due date of the deadline
     * in the format of a String.
     * @return The due date of the deadline.
     */
    public String getDueInString() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return format.format(endDate);
    }

    /**
     * Convert Deadline object to String format.
     * @return The String interpretation of the Deadline.
     */
    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
        return ("[" + typeOfTask + "]" + "[" + statusIcon + "] "
                + description + "(by: "
                + format.format(endDate) + ")");
    }
}
