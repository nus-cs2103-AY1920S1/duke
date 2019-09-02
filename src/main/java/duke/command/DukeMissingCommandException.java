package duke.command;

import duke.error.DukeException;

/**
 * A DukeException which can be thrown when no command is given.
 */
public class DukeMissingCommandException extends DukeException {

    /**
     * Constructs the exception to be thrown when to command is given
     */
    public DukeMissingCommandException() {
        super("No command?! I didn't w-want to do anything anyway!");
    }
}
