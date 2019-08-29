package duke.task;

import duke.exception.DukeException;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
        if (description.isBlank()) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
    }

    @Override
    public String getAscii() {
        return "E | " + super.getAscii() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}