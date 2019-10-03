package duke.task;

import duke.util.DateTime;

/**
 * A sub-class of Task that adds a time
 * by which the task must be completed by.
 */
public class Deadline extends Task {
    protected DateTime by;

    /** Basic constructor for the Deadline sub-class.
     * Takes in a description and a task deadline
     *
     * @param description details of the task.
     * @param by time by which task should be completed.
     */
    public Deadline(String description, DateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object and
     * its various details.
     *
     * @return a string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}