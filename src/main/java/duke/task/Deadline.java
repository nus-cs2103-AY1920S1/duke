package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a type of duke.task.Task object with a fixed time format.
 *
 * @see Task
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructor for duke.task.Deadline.
     *
     * @param description description of the deadline
     * @param by          time in the format of dd/MM/yyyy HHmm
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, Task.TIME_FORMATTER);
    }

    /**
     * Another constructor for duke.task.Deadline.
     *
     * @param description description of the deadline
     * @param by          time in the format of dd/MM/yyyy HHmm
     * @param isDone      boolean value on whether it is done or not
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, Task.TIME_FORMATTER);
    }

    /**
     * Gets the String representation of the deadline.
     *
     * @return the String representation of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the String representation of Deadline object.
     *
     * @return String that would be written into data file
     */
    @Override
    public String toDataString() {
        String[] dateTimeSplit = by.toString().split("T");

        String date = dateTimeSplit[0];
        String time = dateTimeSplit[1];

        String[] dateSplit = date.split("-");
        String[] timeSplit = time.split(":");
        String dataBy = dateSplit[2] + "/" + dateSplit[1] + "/" + dateSplit[0] + " "
                + timeSplit[0] + timeSplit[1];
        return "D | " + super.toDataString() + " | " + dataBy;
    }
}
