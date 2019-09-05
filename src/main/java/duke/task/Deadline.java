package duke.task;

import duke.date.DateValidator;
import duke.date.InvalidDateDukeException;

/**
 * Represents a deadline. Consists of a description which the user intends to perform by a certain deadline.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor for a task of type deadline.
     * @param description Task to be done.
     * @param by Deadline.
     * @throws InvalidTaskDukeException If the task description is invalid.
     * @throws InvalidDateDukeException If the deadline (date) is invalid.
     */
    public Deadline(String description, String by) throws InvalidTaskDukeException, InvalidDateDukeException {
        super(description);
        setBy(by);
    }

    /**
     * Sets the date to the entered date if possible.
     * @param by Date represented as a string.
     * @throws InvalidDateDukeException If the date is invalid.
     */
    public void setBy(String by) throws InvalidDateDukeException {
        DateValidator v = new DateValidator();
        boolean isValid = v.validateDate(by, false);
        if (!isValid) {
            throw new InvalidDateDukeException("Invalid date format! Please ensure your date sticks to this format:\n"
                    + "    Deadlines : \"DD/MM/YYYY HHMM\"\n"
                    + "    Events : \"DD/MM/YYYY HHMM-HHMM\"");
        }
        this.by = by;
    }

    /**
     * Returns the deadline for the task.
     * @return Deadline.
     */
    public String getTime() {
        return by;
    }

    @Override
    public String toString() {
        return "[Deadline] " + super.toString() + " (by: " + by + ")";
    }
}