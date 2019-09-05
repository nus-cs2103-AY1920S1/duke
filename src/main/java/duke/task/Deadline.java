package duke.task;

import duke.util.DateUtil;

import java.util.Date;

import static java.util.Objects.requireNonNull;

/**
 * Represents a {@link Task} that has a deadline.
 */
public class Deadline extends Task {
    protected Date dueDate;

    public Deadline(String description, Date dueDate) {
        super(description);
        this.dueDate = requireNonNull(dueDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateUtil.format(dueDate) + ")";
    }

    @Override
    public String stringify() {
        return "D | " + super.stringify() + " | " + DateUtil.format(dueDate);
    }
}