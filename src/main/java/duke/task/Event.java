package duke.task;

import duke.exception.DukeException;

/**
 * Represents a Task at a particular time eg. 2-4 PM.
 */
public class Event extends Task {

    protected String at;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
        if (description.isBlank()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
    }

    /**
     * Returns an ASCII description of the task.
     *
     * @return ASCII representation of the task.
     */
    @Override
    public String getAscii() {
        return "E | " + super.getAscii() + " | " + at;
    }

    /**
     * Returns a unicode description of the task.
     *
     * @return Unicode representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}