package duke.task;

import duke.exception.DukeException;

/**
 * Represents a simple task to be done.
 */
public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
        if (description.isBlank()) {
            throw new DukeException("OOPS!!! The description of a Todo cannot be empty.");
        }
    }

    /**
     * Returns an ASCII description of the task.
     *
     * @return ASCII representation of the task.
     */
    @Override
    public String getAscii() {
        return "T | " + super.getAscii();
    }

    /**
     * Returns a Unicode description of the task.
     *
     * @return Unicode representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}