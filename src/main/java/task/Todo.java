package task;

import exception.DukeException;

/**
 * Extends Task. Has a task name.
 */
public class Todo extends Task {

    public Todo(String description) throws DukeException {
        super(description);
    }

    @Override
    public String toString() {
        String output = "[T][" + super.getStatusIcon() + "]" + " " + super.description;
        return output;
    }
}
