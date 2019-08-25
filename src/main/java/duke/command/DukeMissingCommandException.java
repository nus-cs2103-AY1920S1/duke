package duke.command;

import duke.DukeException;

public class DukeMissingCommandException extends DukeException {
    public DukeMissingCommandException() {
        super("No command?! I didn't w-want to do anything anyway!");
    }
}
