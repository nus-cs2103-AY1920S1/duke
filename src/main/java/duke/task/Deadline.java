package duke.task;

import java.util.Date;

/**
 * Represents a Deadline, a type of Task that has an expected date of completion.
 */
public class Deadline extends Task {
    private Date by;

    /**
     * Constructs a new Deadline, with the specified description and date of completion.
     * @param description Description of the Deadline.
     * @param by Expected date of completion of the Deadline.
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
        this.type = TaskType.DEADLINE;
    }

    /**
     * Returns the String representation of a Deadline for display purposes.
     * Adds the deadline of the Deadline to the String representation
     * provided by the Task class.
     * @return String representation of a Deadline for display purposes.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
