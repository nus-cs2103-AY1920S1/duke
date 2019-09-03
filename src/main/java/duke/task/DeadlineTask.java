package duke.task;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DeadlineTask extends Task {
    private static final SimpleDateFormat DATE_WRITER = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("EEE, dd MMM yyyy, h:mm a");

    protected Date byDeadline;

    /**
     *  Constructs a <code>DeadlineTask</code> object with a given description and deadline.
     *  @param description <code>String</code> description of this <code>Task</code>.
     *  @param byDeadline deadline of this <code>DeadlineTask</code>, as a <code>Date</code> object.
     */
    public DeadlineTask(String description, Date byDeadline) {
        super(description);
        this.byDeadline = byDeadline;
    }

    /**
     *  Returns the deadline of this <code>DeadlineTask</code>.
     *  @return deadline of this <code>DeadlineTask</code>, as a <code>Date</code>.
     */
    public Date getDeadline() {
        return this.byDeadline;
    }

    /**
     *  {@inheritDoc}
     */
    public String toEncodedString() {
        return String.format(
            "D | %d | %s | %s",
            this.isComplete ? 1 : 0,
            this.description,
            DATE_WRITER.format(this.byDeadline)
        );
    }

    @Override
    public String toString() {
        // Adds the type of the Task and its deadline to the base toString() representation
        return String.format("[D]%s (by: %s)", super.toString(), DATE_FORMATTER.format(this.byDeadline));
    }
}
