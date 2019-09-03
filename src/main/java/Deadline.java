package duke.task;

import java.util.Date;

/**
 * Task containing information about a deadline.
 */
public class Deadline extends Task {
    /**
     * Constructor for Deadline object.
     * 
     * @param description Description of deadline.
     * @param date Date of deadline.
     */
    public Deadline(String description, Date date) {
        super("D", description, date);
    }

    /**
     * Returns String containing information about the deadline.
     * 
     * @return String containing status, description and date of deadline.
     */
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", this.type,
                this.getStatusIcon(),
                this.description,
                this.date.toString());
    }
}
