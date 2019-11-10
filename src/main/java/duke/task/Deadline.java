package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Deadline class indicated with type "D" which contains a date and time on top of other Task components.
 */
public class Deadline extends Task {

    /** Date of the Deadline. */
    private LocalDate startDate;
    /** Time of the Deadline. */
    private LocalTime startTime;

    /**
     * Initialises a Deadline object.
     * @param description Description of the Deadline.
     * @param startDate Date of the Deadline.
     * @param startTime Time of the Deadline.
     */
    public Deadline(String description, LocalDate startDate, LocalTime startTime) {
        super(description);
        this.type = "D";
        this.startDate = startDate;
        this.startTime = startTime;
    }

    /**
     * Gets the date of the Deadline.
     * @return Date of the Deadline.
     */
    @Override
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Gets the time of the Deadline.
     * @return Time of the Deadline.
     */
    @Override
    public LocalTime getStartTime() {
        return this.startTime;
    }

    /**
     * String of the type followed by status icon, description, date and time of Deadline.
     * @return String in the given format.
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + startTime + " " + startDate + ")";
    }
}
