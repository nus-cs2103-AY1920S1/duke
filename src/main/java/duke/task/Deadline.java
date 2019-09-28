package duke.task;

import duke.util.DateManager;
import duke.util.DukeException;

import java.util.Objects;
import java.util.Date;

/**
 * Represents a task that need to be done before a specific date/time.
 */
public class Deadline extends Task {
    /** Date whereby the task need to be done by. */
    private Date deadlineDate;

    /**
     * Constructor for deadline task.
     *
     * @param description Description of deadline task.
     * @param deadlineDateString String specifying the deadline of the task.
     * @throws DukeException If deadlineDateString is not in correct format.
     */
    public Deadline(String description, String deadlineDateString) throws DukeException {
        super(description);

        assert !deadlineDateString.equals("") : "Description of Task should not be empty.";

        this.deadlineDate = DateManager.stringToDate(deadlineDateString);
    }

    /**
     * Returns the print format of the deadline task.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateManager.dateToString(deadlineDate) + ")";
    }

    /**
     * Returns the save format of the deadline task.
     *
     * @return Formatted string to be saved in storage file.
     */
    @Override
    public String getSaveString() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | "
                + DateManager.dateToString(deadlineDate);
    }

    public Date getDeadlineDate() {
        return deadlineDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Deadline)) {
            return false;
        }

        Deadline deadline = (Deadline) obj;
        return this.description.equals(deadline.getDescription())
                && this.deadlineDate.equals(deadline.getDeadlineDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.description, this.deadlineDate);
    }
}
