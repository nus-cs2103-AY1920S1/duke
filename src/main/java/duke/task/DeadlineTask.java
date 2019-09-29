package duke.task;

import static duke.util.DateTime.parseDate;
import static duke.util.DateTime.snoozeDate;

import duke.util.exception.DukeException;
import java.util.Date;

/**
 * Represents a Deadline, a type of Task that has an expected date of completion.
 */
public class DeadlineTask extends Task {
    private Date by;

    /**
     * Constructs a new Deadline, with the specified description and date of completion.
     * @param description Description of the Deadline.
     * @param byString String representing expected date of completion of the Deadline.
     */
    public DeadlineTask(String description, String byString) throws DukeException {
        super(description);
        this.type = TaskType.DEADLINE;

        this.by = parseDate(byString);
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

    @Override
    public void snooze() {
        this.by = snoozeDate(this.by);
    }
}
