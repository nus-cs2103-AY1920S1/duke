package duke.task;

import duke.exception.DukeException;

/**
 * Represents a task due by a particular time.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        if (description.isBlank()) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    /**
     * Returns an ASCII description of the task.
     *
     * @return ASCII representation of the task.
     */
    @Override
    public String getAscii() {
        return "D | " + super.getAscii() + " | " + by;
    }

    /**
     * Returns a Unicode description of the task.
     *
     * @return Unicode representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}