package duke.task;

import duke.exception.DukeException;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        if (description.isBlank()) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    @Override
    public String getAscii() {
        return "D | " + super.getAscii() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}