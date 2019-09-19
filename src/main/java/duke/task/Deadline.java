package duke.task;

import duke.Duke;

import java.util.Date;

public class Deadline extends Task {
    private Date by;

    /**
     * Constructs a new deadline.
     *
     * @param description Description of the deadline.
     * @param by          Date of the deadline.
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Generates the deadline's string representation.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Duke.DATE_FORMATTER.format(this.by));
    }
}
