package duke.task;

import java.util.ArrayList;
import java.util.Date;

/**
 * An object representation of a user task to be completed by some deadline.
 */
public class DeadlineTask extends Task {
    protected Date byDeadline;

    /**
     * Constructs a <code>DeadlineTask</code> object with a given description and deadline.
     * 
     * @param description <code>String</code> description of this <code>Task</code>.
     * @param byDeadline deadline of this <code>DeadlineTask</code>, as a <code>Date</code> object.
     * @param tags an <code>ArrayList</code> of <code>String</code> tags for this task.
     */
    public DeadlineTask(String description, Date byDeadline, ArrayList<String> tags) {
        super(description, tags);
        this.byDeadline = byDeadline;
    }

    /**
     * Returns the deadline of this <code>DeadlineTask</code>.
     * 
     * @return deadline of this <code>DeadlineTask</code>, as a <code>Date</code>.
     */
    public Date getDeadline() {
        return this.byDeadline;
    }

    /**
     * {@inheritDoc}
     */
    public String toEncodedString() {
        return String.format(
            "D | %d | %s | %s",
            this.isComplete ? 1 : 0,
            this.description,
            Task.DATE_WRITER.format(this.byDeadline)
        );
    }

    @Override
    public String toString() {
        // Adds the type of the Task and its deadline to the base toString() representation
        return String.format("[D]%s (by: %s)", super.toString(), Task.DATE_FORMATTER.format(this.byDeadline));
    }
}
