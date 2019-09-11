package duke.task;

import duke.DukeException;

/**
 * Handles a certain type of Task with a description and date and time when event will happen.
 */
public class Event extends Task {
    protected DateAndTime at;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = new DateAndTime(at);
    }

    @Override
    public String toSave() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + at.toSave();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
