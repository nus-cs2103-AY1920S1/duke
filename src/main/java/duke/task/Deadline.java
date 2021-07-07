package duke.task;

import duke.DukeException;

/**
 * Handles a certain type of Task with a description and date and time when deadline will happen.
 */
public class Deadline extends Task {
    protected DateAndTime by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = new DateAndTime(by);
    }

    public Deadline(String description, String by, int freq) throws DukeException {
        super(description, freq);
        this.by = new DateAndTime(by);
    }

    public DateAndTime getDateAndTime() {
        return by;
    }

    @Override
    public String toSave() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + frequency + " | " + by.toSave();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
