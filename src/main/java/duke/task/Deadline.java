package duke.task;

import duke.util.DateUtil;

import java.util.Date;

/**
 * Represents a {@link Task} that has a deadline.
 */
public class Deadline extends Task {
    protected Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateUtil.format(by) + ")";
    }

    @Override
    public String stringify() {
        return "D | " + super.stringify() + " | " + DateUtil.format(by);
    }
}